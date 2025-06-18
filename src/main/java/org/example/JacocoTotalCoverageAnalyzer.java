package org.example;

import org.jacoco.core.analysis.Analyzer;
import org.jacoco.core.analysis.CoverageBuilder;
import org.jacoco.core.analysis.IBundleCoverage;
import org.jacoco.core.analysis.IClassCoverage;
import org.jacoco.core.data.ExecutionDataReader;
import org.jacoco.core.data.ExecutionDataStore;
import org.jacoco.core.data.SessionInfoStore;
import org.jacoco.core.runtime.WildcardMatcher;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JacocoTotalCoverageAnalyzer {
    private final List<WildcardMatcher> excludes = new ArrayList<>();

    public JacocoTotalCoverageAnalyzer() {
        initializeDefaultExcludes();
    }

    private void initializeDefaultExcludes() {
        // Hardcoded exclusions from the provided configuration
        List<String> defaultExcludes = Arrays.asList(
                "com.ing.ipa.ha.IpaHedgeAccountingApplication",
                "com.ing.ipa.ha.api.**",
                "com.ing.ipa.ha.model.**",
                "com.ing.ipa.ha.murex.**",
                "com.ing.ipa.ha.flexing.**",
                "com.ing.ipa.ha.client.JmsClient",
                "com.ing.ipa.ha.documentation.DateProvider",
                "com.ing.ipa.ha.mapper.LowEnvironmentHedgeMapper*",
                "com.ing.ipa.ha.mapper.HedgeComponentCreateMapper*",
                "**.SufficiencyTestBucketResultMapper*",
                "**.CapacitySimulationTestBucketResultMapper*",
                "**.TestContextMapper*",
                "com.ing.ipa.ha.repository.entity.**",
                "com.ing.ipa.ha.config.**",
                "com.ing.ipa.ha.repository.converters.GenericJsonConverter",
                "com.ing.ipa.ha.service.TestResultsService",
                "com.ing.ipa.ha.client.GrpcClientConfiguration",
                "com.ing.ipa.ha.service.ErrorService",
                "com.ing.ipa.ha.service.TradestoreService",
                "com.ing.ipa.ha.controller.pagination.ErrorRsqlSupport",
                "com.ing.ipa.ha.mapper.ErrorMapperImpl",
                "com.ing.ipa.ha.client.GrpcBookServiceConfig",
                "com.ing.ipa.ha.mapper.HedgeRelationDashboardProjectionMapperImpl",
                "com.ing.ipa.ha.controller.pagination.HedgeDashboardViewSupport",
                "com.ing.ipa.ha.client.GrpcBookServiceConfig",
                "com.ing.ipa.ha.fsd.GrpcBookService",
                "com.ing.ipa.ha.client.GrpcBookServiceConfig*",
                "**.EffectivenessTestResultsHandler",
                "**.SufficiencyTestResultsHandler",
                "**.CapacitySimulationTestResultsHandler",
                "**.EffectivenessDatapointMapperImpl",
                "com.ing.ipa.ha.listener.**",
                "com.ing.ipa.ha.service.eod.EodRunService",
                "com.ing.ipa.ha.utils.ExcelUtils",
                "com.ing.ipa.ha.utils.JAXBUtils",
                "com.ing.ipa.ha.utils.CsvPrinterDecorator",
                "com.ing.ipa.ha.enums.PayerReceiverType",
                "**.HedgedFilterConfigurationMapperImpl",
                "**.MailWarningsConfigurationMapperImpl",
                "**.PagedMafvhFilterDetailsMapperImpl",
                "**.HedgingBookDetailsMapperImpl",
                "**.BotsFileConfigurationMapperImpl",
                "**.InnerBookDetailsMapperImpl",
                "**.HedgeEffectivenessBoundsConfigurationMapperImpl",
                "**.MacroprogramMapperImpl",
                "**.HedgeEffectivenessDatapointsConfigurationMapperImpl",
                "**.HedgeEffectivenessTestDtoMapperImpl",
                "**.HedgeSufficiencyTestDtoMapperImpl",
                "**.CapacitySimulationTestDtoMapperImpl",
                "**.BasicHedgeEffectivenessTestDtoMapperImpl",
                "**.BasicHedgeSufficiencyTestDtoMapperImpl",
                "**.BasicCapacitySimulationTestDtoMapperImpl",
                "**.PagedHedgeEffectivenessTestDatapointMapperImpl",
                "**.PagedBasicHedgeEffectivenessTestsDtoMapperImpl",
                "**.PagedBasicHedgeSufficiencyTestsDtoMapperImpl",
                "**.PagedHedgeEffectivenessBoundsConfigurationsMapperImpl",
                "**.PagedHedgeEffectivenessDatapointsConfigurationsMapperImpl",
                "**.PagedHedgingBookDetailsMapperImpl",
                "**.PagedHedgedFilterConfigurationDetailsMapperImpl",
                "**.PagedMailWarningsConfigurationMapperImpl",
                "**.PagedHedgeQueryMapperImpl",
                "**.TestTypeMapperImpl",
                "**.PagedHedgeRelationBlotterMapperImpl",
                "**.DatapointConfigurationFilterValidator",
                "**.IpaHedgeAccountingException",
                "**.HedgeRelationExternalizationItemMapperImpl",
                "**.ExternalHedgingSourceBookMapperImpl",
                "**.PagedExternalHedgingSourceBookControllerMapperImpl",
                "**.PagedExternalCounterpartyMapperImpl",
                "**.ExternalCounterpartyMapperImpl",
                "**.ProtobufParserException",
                "**.NotionalSign",
                "**.ExternalizationHedgingTradesMapperImpl",
                "**.ExternalHedgingComponentMapperImpl",
                "**.TradeConstants",
                "**.CapacitySimulationTestMapperImpl",
                "**.HedgeTestAnomalyMapperImpl",
                "**.CapacitySimulationInputDataMapper*",
                "**.CapacitySimulationInputDataDtoMapper*",
                "**.MailWarningProcessMapperImpl",
                "**.ExternalizationBooksMapper*",
                "**.PagedExternalizationBooksMapper*",
                "**.mapper.migration.**",
                "**.CsvFileParser",
                "**.ZipArchiveCsvValidator",
                "**.MigrationMapperImpl",
                "**.EntityGraphEnversRevisionRepositoryFactoryBean*",

                // Additional exclusions
                "com.ing.ipa.ha.engine.IpaHaTestServiceApplication",
                "com.ing.ipa.ha.engine.client.**",
                "com.ing.ipa.ha.engine.listener.**",
                "com.ing.ipa.ha.engine.configuration.**",
                "com.ing.ipa.ha.api.**",  // Note: This is already in the list above, but included again for completeness
                "com.ing.ipa.ha.engine.mock.**",
                "com.ing.ipa.ha.engine.repository.**",
                "com.ing.ipa.common.model.**",
                "com.ing.ipa.ha.engine.exception.**",
                "com.ing.ipa.ha.engine.Result$Continuation",
                "com.ing.ipa.ha.engine.service.TradeStoreQueryService",
                "com.ing.ipa.ha.engine.exception.ProtobufParserException",  // Already covered by exception.** pattern above
                "com.ing.ipa.ha.engine.sufficiency.SufficiencyTestResultCalculator",
                "com.ing.ipa.ha.engine.utils.ExcelUtils"
        );

        for (String pattern : defaultExcludes) {
            // Convert the class path format from XML to WildcardMatcher format
            // Replace any remaining .class extensions and convert slashes to dots
            String formattedPattern = pattern
                    .replace(".class", "")   // Remove .class extension if present
                    .replace('/', '.');      // Convert slashes to dots

            this.excludes.add(new WildcardMatcher(formattedPattern));
        }
    }

    public void analyzeCoverage(String executionDataFile, String classesDirectory) {
        try {
            // Load execution data
            ExecutionDataStore executionDataStore = new ExecutionDataStore();
            SessionInfoStore sessionInfoStore = new SessionInfoStore();

            try (FileInputStream fis = new FileInputStream(executionDataFile)) {
                ExecutionDataReader reader = new ExecutionDataReader(fis);
                reader.setExecutionDataVisitor(executionDataStore);
                reader.setSessionInfoVisitor(sessionInfoStore);
                reader.read();
            }

            // Create coverage builder with exclusion support
            CoverageBuilder coverageBuilder = new CoverageBuilder() {
                @Override
                public void visitCoverage(IClassCoverage coverage) {
                    if (!isExcluded(coverage.getName().replace('/', '.'))) {
                        super.visitCoverage(coverage);
                    } else {
                        System.out.println("Excluded: " + coverage.getName());
                    }
                }
            };
            Analyzer analyzer = new Analyzer(executionDataStore, coverageBuilder);

            // Analyze class files
            analyzer.analyzeAll(Paths.get(classesDirectory).toFile());

            // Get the bundle coverage
            IBundleCoverage bundleCoverage = coverageBuilder.getBundle("Total Coverage");

            // Calculate total branch and instruction coverage
            int totalBranchMissed = 0;
            int totalBranchCovered = 0;
            int totalInstructionMissed = 0;
            int totalInstructionCovered = 0;

            for (IClassCoverage classCoverage : coverageBuilder.getClasses()) {
                // Branch coverage
                totalBranchMissed += classCoverage.getBranchCounter().getMissedCount();
                totalBranchCovered += classCoverage.getBranchCounter().getCoveredCount();

                // Instruction coverage
                totalInstructionMissed += classCoverage.getInstructionCounter().getMissedCount();
                totalInstructionCovered += classCoverage.getInstructionCounter().getCoveredCount();

                // Optional: Print coverage for each class
                System.out.printf("Class: %s%n", classCoverage.getName());
                System.out.printf("  Branch Coverage: %.2f%%%n",
                        calculatePercentage(classCoverage.getBranchCounter().getCoveredCount(),
                                classCoverage.getBranchCounter().getTotalCount()));
                System.out.printf("  Instruction Coverage: %.2f%%%n",
                        calculatePercentage(classCoverage.getInstructionCounter().getCoveredCount(),
                                classCoverage.getInstructionCounter().getTotalCount()));
            }

            // Print total coverage
            System.out.println("\nTotal Coverage Across All Classes:");
            System.out.printf("Total Branch Coverage: %.2f%%%n",
                    calculatePercentage(totalBranchCovered, totalBranchMissed + totalBranchCovered));
            System.out.printf("Total Instruction Coverage: %.2f%%%n",
                    calculatePercentage(totalInstructionCovered, totalInstructionMissed + totalInstructionCovered));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean isExcluded(String name) {
        for (WildcardMatcher matcher : excludes) {
            if (matcher.matches(name)) {
                return true;
            }
        }
        return false;
    }

    private static double calculatePercentage(int covered, int total) {
        return total > 0 ? (covered * 100.0 / total) : 0.0;
    }

    public static void main(String[] args) {
//        if (args.length < 2) {
//            System.out.println("Usage: java JacocoTotalCoverageAnalyzer <path_to_jacoco.exec> <path_to_classes_directory> [exclude_pattern1] [exclude_pattern2] ...");
//            System.exit(1);
//        }

//        String[] excludePatterns = null;
//        if (args.length > 2) {
//            excludePatterns = Arrays.copyOfRange(args, 2, args.length);
//        }

        // 1. Ha web
        JacocoTotalCoverageAnalyzer analyzer = new JacocoTotalCoverageAnalyzer();
//        analyzer.analyzeCoverage("/Users/jn93pw/Downloads/jacoco (9).exec",
//                "/Users/jn93pw/projects/P01818-ipa-hedge-accounting/ipa-ha-service/ipa-ha-web/target/classes");

        // 2. Test Engine
        analyzer.analyzeCoverage("/Users/jn93pw/Downloads/jacoco (10).exec",
                "/Users/jn93pw/projects/P01818-ipa-hedge-accounting/ipa-ha-test-service/ipa-ha-test-service-engine/target/classes");
    }
}