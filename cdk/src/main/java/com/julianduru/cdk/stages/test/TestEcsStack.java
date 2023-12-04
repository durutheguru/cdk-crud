package com.julianduru.cdk.stages.test;

import com.julianduru.cdk.Main;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;
import software.amazon.awscdk.services.ecs.*;
import software.amazon.awscdk.services.ecs.patterns.ApplicationLoadBalancedFargateService;
import software.amazon.awscdk.services.ecs.patterns.ApplicationLoadBalancedTaskImageOptions;
import software.amazon.awscdk.services.elasticloadbalancingv2.HealthCheck;
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

        setup();
    }


    private void setup() {
        service = ApplicationLoadBalancedFargateService.Builder
            .create(this, Main.prefixApp("ECSFargateService"))
            .taskImageOptions(
                ApplicationLoadBalancedTaskImageOptions.builder()
                    .image(
                        ContainerImage.fromRegistry("public.ecr.aws/h9p9w3g7/crud:0.1.0-alpha.84")
                    )
                    .enableLogging(true)
                    .logDriver(
                        LogDriver.awsLogs(
                            AwsLogDriverProps.builder()
                                .streamPrefix("str-prfix")
                                .build()
                        )
                    )
                    .build()
            )
            .publicLoadBalancer(true)
            .assignPublicIp(true)
            .build();

    }


//    private void setup() {
//        service = ApplicationLoadBalancedFargateService.Builder
//            .create(this, Main.prefixApp("ECSFargateService"))
//            .taskImageOptions(
//                ApplicationLoadBalancedTaskImageOptions.builder()
//                    .image(
//                        ContainerImage.fromRegistry("public.ecr.aws/h9p9w3g7/crud:0.1.0-alpha.84")
//                    )
//                    .containerPort(8080)
//                    .enableLogging(true)
//                    .logDriver(
//                        LogDriver.awsLogs(
//                            AwsLogDriverProps.builder()
//                                .streamPrefix("str-prfix")
//                                .build()
//                        )
//                    )
//                    .build()
//            )
//            .publicLoadBalancer(true)
//            .assignPublicIp(true)
//            .build();
//
//    }


//    private void setup() {
//
////        Cluster cluster = new Cluster(this, Main.prefixApp("ECSCluster"));
//
////        FargateTaskDefinition taskDefinition = FargateTaskDefinition.Builder.create(this, "ECSTaskDefinition")
////                .build();
////
////        taskDefinition.addContainer(
////            "crud-container",
////            ContainerDefinitionOptions.builder()
////                .image(ContainerImage.fromRegistry("public.ecr.aws/h9p9w3g7/crud:0.1.0-alpha.84"))
////                .portMappings(
////                    List.of(
////                        PortMapping.builder()
////                            .containerPort(8080)
////                            .build()
////                    )
////                )
////                .logging(
////                    LogDriver.awsLogs(
////                        AwsLogDriverProps.builder()
////                            .streamPrefix("str-prfix")
////                            .build()
////                    )
////                )
////                .build()
////        );
//
//        service = ApplicationLoadBalancedFargateService.Builder
//            .create(this, Main.prefixApp("ECSFargateService"))
//            .taskImageOptions(
//                ApplicationLoadBalancedTaskImageOptions.builder()
//                    .image(
//                        ContainerImage.fromRegistry("public.ecr.aws/h9p9w3g7/crud:0.1.0-alpha.84")
//                    )
//                    .containerPort(8080)
//                    .enableLogging(true)
//                    .logDriver(
//                        LogDriver.awsLogs(
//                            AwsLogDriverProps.builder()
//                                .streamPrefix("str-prfix")
//                                .build()
//                        )
//                    )
//                    .build()
//            )
//            .publicLoadBalancer(true)
//            .assignPublicIp(true)
//            .build();
//
////        service.getTargetGroup().configureHealthCheck(
////            HealthCheck.builder()
////                .path("/actuator/health")
////                .port("8080")
////                .build()
////        );
//    }


    public ApplicationLoadBalancedFargateService getApplicationLoadBalancedFargateService() {
        return service;
    }


}

