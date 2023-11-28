package com.julianduru.cdk.stages.test;

import com.julianduru.cdk.Main;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;
import software.amazon.awscdk.services.ecs.*;
import software.amazon.awscdk.services.ecs.patterns.ApplicationLoadBalancedFargateService;
import software.constructs.Construct;

import java.util.List;
import java.util.Map;

/**
 * created by Julian Dumebi Duru on 25/11/2023
 */
public class TestEcsStack extends Stack {

    private ApplicationLoadBalancedFargateService service;


    public TestEcsStack(final Construct scope, final String id) {
        this(scope, id, null, null);
    }


    public TestEcsStack(final Construct scope, final String id, final StackProps props, final Map<String, String> variableMap) {
        super(scope, id, props);

//        Cluster cluster = new Cluster(this, Main.prefixApp("ECSCluster"));

        FargateTaskDefinition taskDefinition = FargateTaskDefinition.Builder.create(this, "ECSTaskDefinition")
                .build();
        taskDefinition.addContainer(
            "crud-container",
            ContainerDefinitionOptions.builder()
                .image(ContainerImage.fromRegistry("public.ecr.aws/h9p9w3g7/crud:0.1.0-alpha.77"))
                .portMappings(
                    List.of(
                        PortMapping.builder()
                            .containerPort(8080)
                            .build()
                    )
                )
                .logging(
                    LogDriver.awsLogs(
                        AwsLogDriverProps.builder()
                            .streamPrefix("str-prfix")
                            .build()
                    )
                )
                .build()
        );

        service = ApplicationLoadBalancedFargateService.Builder
            .create(this, Main.prefixApp("ECSFargateService"))
//            .cluster(cluster)
            .taskDefinition(taskDefinition)
            .publicLoadBalancer(true)
//            .lis
            .build();
    }


    public ApplicationLoadBalancedFargateService getApplicationLoadBalancedFargateService() {
        return service;
    }


}
