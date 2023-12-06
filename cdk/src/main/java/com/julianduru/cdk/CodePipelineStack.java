package com.julianduru.cdk;

import com.julianduru.cdk.stages.test.TestEcsStack;
import software.amazon.awscdk.RemovalPolicy;
import software.amazon.awscdk.SecretValue;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;
import software.amazon.awscdk.services.codebuild.*;
import software.amazon.awscdk.services.codepipeline.Artifact;
import software.amazon.awscdk.services.codepipeline.Pipeline;
import software.amazon.awscdk.services.codepipeline.PipelineProps;
import software.amazon.awscdk.services.codepipeline.StageProps;
import software.amazon.awscdk.services.codepipeline.actions.CodeBuildAction;
import software.amazon.awscdk.services.codepipeline.actions.EcsDeployAction;
import software.amazon.awscdk.services.codepipeline.actions.GitHubSourceAction;
import software.amazon.awscdk.services.s3.Bucket;
import software.amazon.awscdk.services.s3.BucketProps;
import software.constructs.Construct;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

public class CodePipelineStack extends Stack {

    private String githubOwner;

    private String githubRepo;

    private String githubBranch;

    private StackProps stackProps;


    public CodePipelineStack(final Construct scope, final String id, final StackProps props, final Map<String, String> variableMap) {
        super(scope, id, props);

        this.githubOwner = variableMap.get("githubOwner");
        this.githubRepo = variableMap.get("githubRepo");
        this.githubBranch = variableMap.get("githubBranch");

        this.stackProps = props;

        Bucket codePipelineBucket = createCodePipelineBucket(variableMap.get("codePipelineBucket"));
        Project buildProject = createBuildProject();

        createPipeline(codePipelineBucket, buildProject, variableMap);
    }


    private Bucket createCodePipelineBucket(String codePipelineBucketName) {
        return new Bucket(this,
            Main.prefixApp("code-pipeline-ecs-resource-bucket").toLowerCase(),
            BucketProps.builder()
//                .bucketName(Main.prefixApp("code-pipeline-bucket-18938817843").toLowerCase())
                .bucketName(Main.prefixApp(codePipelineBucketName).toLowerCase())
                .removalPolicy(RemovalPolicy.DESTROY)
                .autoDeleteObjects(true)
                .build()
        );
    }


    private Project createBuildProject() {
        return PipelineProject.Builder.create(this, Main.prefixApp("ECS-CodePipelineProject"))
            .environment(BuildEnvironment.builder().buildImage(LinuxBuildImage.STANDARD_7_0).build())
            .buildSpec(BuildSpec.fromSourceFilename("buildspec.yml"))
            .build();
    }


    private Pipeline createPipeline(Bucket codePipelineBucket, Project buildProject, Map<String, String> variableMap) {
        return new Pipeline(this, Main.prefixApp("EBS-CodePipeline"), PipelineProps.builder()
            .stages(
                Arrays.asList(
                    StageProps.builder()
                        .stageName("Source")
                        .actions(
                            Collections.singletonList(
                                GitHubSourceAction.Builder.create()
                                    .actionName("GitHubSourceAction")
                                    .owner(githubOwner)
                                    .repo(githubRepo)
                                    .branch(githubBranch)
                                    .oauthToken(SecretValue.secretsManager("github-token"))
                                    .output(
                                        Artifact.artifact(
                                            Main.prefixApp("ECS-SourceArtifact")
                                        )
                                    )
                                    .build()
                            )
                        )
                        .build(),

                    StageProps.builder()
                        .stageName("Build")
                        .actions(
                            Collections.singletonList(
                                CodeBuildAction.Builder.create()
                                    .actionName("CodeBuild")
                                    .project(buildProject)
                                    .input(
                                        Artifact.artifact(
                                            Main.prefixApp("ECS-SourceArtifact")
                                        )
                                    )
                                    .outputs(
                                        Collections.singletonList(
                                            Artifact.artifact(
                                                Main.prefixApp("ECS-BuildArtifact")
                                            )
                                        )
                                    )
                                    .build()
                            )
                        )
                        .build(),

                    StageProps.builder()
                        .stageName("Test-Deploy")
                        .actions(Collections.singletonList(getECSDeployAction(variableMap)))
                        .build()
                )
            )
            .artifactBucket(codePipelineBucket)
            .build()
        );
    }


    private EcsDeployAction getECSDeployAction(final Map<String, String> variableMap) {
        TestEcsStack ecsStack = new TestEcsStack(
            this, Main.prefixApp("ecsStackId"), stackProps, variableMap
        );

        return EcsDeployAction.Builder.create()
            .actionName(Main.prefixApp("ECSDeployAction"))
            .service(ecsStack.getApplicationLoadBalancedFargateService().getService())
            .input(Artifact.artifact(Main.prefixApp("ECS-BuildArtifact")))
            .build();
    }


}



