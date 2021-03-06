/*
 * Copyright 2010-2019 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.fir;

import com.intellij.testFramework.TestDataPath;
import org.jetbrains.kotlin.test.JUnit3RunnerWithInners;
import org.jetbrains.kotlin.test.KotlinTestUtils;
import org.jetbrains.kotlin.test.TestMetadata;
import org.junit.runner.RunWith;

import java.io.File;
import java.util.regex.Pattern;

/** This class is generated by {@link org.jetbrains.kotlin.generators.tests.TestsPackage}. DO NOT MODIFY MANUALLY */
@SuppressWarnings("all")
@TestMetadata("compiler/fir/resolve/testData/diagnostics")
@TestDataPath("$PROJECT_ROOT")
@RunWith(JUnit3RunnerWithInners.class)
public class FirDiagnosticsTestGenerated extends AbstractFirDiagnosticsTest {
    private void runTest(String testDataFilePath) throws Exception {
        KotlinTestUtils.runTest(this::doTest, this, testDataFilePath);
    }

    public void testAllFilesPresentInDiagnostics() throws Exception {
        KotlinTestUtils.assertAllTestsPresentByMetadata(this.getClass(), new File("compiler/fir/resolve/testData/diagnostics"), Pattern.compile("^(.+)\\.kt$"), true);
    }

    @TestMetadata("compiler/fir/resolve/testData/diagnostics/callableReferences")
    @TestDataPath("$PROJECT_ROOT")
    @RunWith(JUnit3RunnerWithInners.class)
    public static class CallableReferences extends AbstractFirDiagnosticsTest {
        private void runTest(String testDataFilePath) throws Exception {
            KotlinTestUtils.runTest(this::doTest, this, testDataFilePath);
        }

        public void testAllFilesPresentInCallableReferences() throws Exception {
            KotlinTestUtils.assertAllTestsPresentByMetadata(this.getClass(), new File("compiler/fir/resolve/testData/diagnostics/callableReferences"), Pattern.compile("^(.+)\\.kt$"), true);
        }

        @TestMetadata("beyoundCalls.kt")
        public void testBeyoundCalls() throws Exception {
            runTest("compiler/fir/resolve/testData/diagnostics/callableReferences/beyoundCalls.kt");
        }

        @TestMetadata("companions.kt")
        public void testCompanions() throws Exception {
            runTest("compiler/fir/resolve/testData/diagnostics/callableReferences/companions.kt");
        }

        @TestMetadata("constructors.kt")
        public void testConstructors() throws Exception {
            runTest("compiler/fir/resolve/testData/diagnostics/callableReferences/constructors.kt");
        }

        @TestMetadata("differentLevels.kt")
        public void testDifferentLevels() throws Exception {
            runTest("compiler/fir/resolve/testData/diagnostics/callableReferences/differentLevels.kt");
        }

        @TestMetadata("extensionReceiverInference.kt")
        public void testExtensionReceiverInference() throws Exception {
            runTest("compiler/fir/resolve/testData/diagnostics/callableReferences/extensionReceiverInference.kt");
        }

        @TestMetadata("implicitTypes.kt")
        public void testImplicitTypes() throws Exception {
            runTest("compiler/fir/resolve/testData/diagnostics/callableReferences/implicitTypes.kt");
        }

        @TestMetadata("inferenceFromCallableReferenceType.kt")
        public void testInferenceFromCallableReferenceType() throws Exception {
            runTest("compiler/fir/resolve/testData/diagnostics/callableReferences/inferenceFromCallableReferenceType.kt");
        }

        @TestMetadata("inferenceFromExpectedType.kt")
        public void testInferenceFromExpectedType() throws Exception {
            runTest("compiler/fir/resolve/testData/diagnostics/callableReferences/inferenceFromExpectedType.kt");
        }

        @TestMetadata("javaStatic.kt")
        public void testJavaStatic() throws Exception {
            runTest("compiler/fir/resolve/testData/diagnostics/callableReferences/javaStatic.kt");
        }

        @TestMetadata("manyCandidatesInference.kt")
        public void testManyCandidatesInference() throws Exception {
            runTest("compiler/fir/resolve/testData/diagnostics/callableReferences/manyCandidatesInference.kt");
        }

        @TestMetadata("manyInnerCandidates.kt")
        public void testManyInnerCandidates() throws Exception {
            runTest("compiler/fir/resolve/testData/diagnostics/callableReferences/manyInnerCandidates.kt");
        }

        @TestMetadata("manyInnerManyOuterCandidates.kt")
        public void testManyInnerManyOuterCandidates() throws Exception {
            runTest("compiler/fir/resolve/testData/diagnostics/callableReferences/manyInnerManyOuterCandidates.kt");
        }

        @TestMetadata("manyInnermanyOuterCandidatesAmbiguity.kt")
        public void testManyInnermanyOuterCandidatesAmbiguity() throws Exception {
            runTest("compiler/fir/resolve/testData/diagnostics/callableReferences/manyInnermanyOuterCandidatesAmbiguity.kt");
        }

        @TestMetadata("manyOuterCandidates.kt")
        public void testManyOuterCandidates() throws Exception {
            runTest("compiler/fir/resolve/testData/diagnostics/callableReferences/manyOuterCandidates.kt");
        }

        @TestMetadata("properties.kt")
        public void testProperties() throws Exception {
            runTest("compiler/fir/resolve/testData/diagnostics/callableReferences/properties.kt");
        }

        @TestMetadata("sam.kt")
        public void testSam() throws Exception {
            runTest("compiler/fir/resolve/testData/diagnostics/callableReferences/sam.kt");
        }

        @TestMetadata("simpleClassReceiver.kt")
        public void testSimpleClassReceiver() throws Exception {
            runTest("compiler/fir/resolve/testData/diagnostics/callableReferences/simpleClassReceiver.kt");
        }

        @TestMetadata("simpleExpressionReceiver.kt")
        public void testSimpleExpressionReceiver() throws Exception {
            runTest("compiler/fir/resolve/testData/diagnostics/callableReferences/simpleExpressionReceiver.kt");
        }

        @TestMetadata("simpleNoReceiver.kt")
        public void testSimpleNoReceiver() throws Exception {
            runTest("compiler/fir/resolve/testData/diagnostics/callableReferences/simpleNoReceiver.kt");
        }

        @TestMetadata("varProperties.kt")
        public void testVarProperties() throws Exception {
            runTest("compiler/fir/resolve/testData/diagnostics/callableReferences/varProperties.kt");
        }

        @TestMetadata("compiler/fir/resolve/testData/diagnostics/callableReferences/fromBasicDiagnosticTests")
        @TestDataPath("$PROJECT_ROOT")
        @RunWith(JUnit3RunnerWithInners.class)
        public static class FromBasicDiagnosticTests extends AbstractFirDiagnosticsTest {
            private void runTest(String testDataFilePath) throws Exception {
                KotlinTestUtils.runTest(this::doTest, this, testDataFilePath);
            }

            public void testAllFilesPresentInFromBasicDiagnosticTests() throws Exception {
                KotlinTestUtils.assertAllTestsPresentByMetadata(this.getClass(), new File("compiler/fir/resolve/testData/diagnostics/callableReferences/fromBasicDiagnosticTests"), Pattern.compile("^(.+)\\.kt$"), true);
            }

            @TestMetadata("ambiguityWhenNoApplicableCallableReferenceCandidate.kt")
            public void testAmbiguityWhenNoApplicableCallableReferenceCandidate() throws Exception {
                runTest("compiler/fir/resolve/testData/diagnostics/callableReferences/fromBasicDiagnosticTests/ambiguityWhenNoApplicableCallableReferenceCandidate.kt");
            }

            @TestMetadata("applicableCallableReferenceFromDistantScope.kt")
            public void testApplicableCallableReferenceFromDistantScope() throws Exception {
                runTest("compiler/fir/resolve/testData/diagnostics/callableReferences/fromBasicDiagnosticTests/applicableCallableReferenceFromDistantScope.kt");
            }

            @TestMetadata("chooseCallableReferenceDependingOnInferredReceiver.kt")
            public void testChooseCallableReferenceDependingOnInferredReceiver() throws Exception {
                runTest("compiler/fir/resolve/testData/diagnostics/callableReferences/fromBasicDiagnosticTests/chooseCallableReferenceDependingOnInferredReceiver.kt");
            }

            @TestMetadata("commonSupertypeFromReturnTypesOfCallableReference.kt")
            public void testCommonSupertypeFromReturnTypesOfCallableReference() throws Exception {
                runTest("compiler/fir/resolve/testData/diagnostics/callableReferences/fromBasicDiagnosticTests/commonSupertypeFromReturnTypesOfCallableReference.kt");
            }

            @TestMetadata("eagerAndPostponedCallableReferences.kt")
            public void testEagerAndPostponedCallableReferences() throws Exception {
                runTest("compiler/fir/resolve/testData/diagnostics/callableReferences/fromBasicDiagnosticTests/eagerAndPostponedCallableReferences.kt");
            }

            @TestMetadata("eagerResolveOfSingleCallableReference.kt")
            public void testEagerResolveOfSingleCallableReference() throws Exception {
                runTest("compiler/fir/resolve/testData/diagnostics/callableReferences/fromBasicDiagnosticTests/eagerResolveOfSingleCallableReference.kt");
            }

            @TestMetadata("moreSpecificAmbiguousExtensions.kt")
            public void testMoreSpecificAmbiguousExtensions() throws Exception {
                runTest("compiler/fir/resolve/testData/diagnostics/callableReferences/fromBasicDiagnosticTests/moreSpecificAmbiguousExtensions.kt");
            }

            @TestMetadata("multipleOutersAndMultipleCallableReferences.kt")
            public void testMultipleOutersAndMultipleCallableReferences() throws Exception {
                runTest("compiler/fir/resolve/testData/diagnostics/callableReferences/fromBasicDiagnosticTests/multipleOutersAndMultipleCallableReferences.kt");
            }

            @TestMetadata("noAmbiguityBetweenTopLevelAndMemberProperty.kt")
            public void testNoAmbiguityBetweenTopLevelAndMemberProperty() throws Exception {
                runTest("compiler/fir/resolve/testData/diagnostics/callableReferences/fromBasicDiagnosticTests/noAmbiguityBetweenTopLevelAndMemberProperty.kt");
            }

            @TestMetadata("overloadsBound.kt")
            public void testOverloadsBound() throws Exception {
                runTest("compiler/fir/resolve/testData/diagnostics/callableReferences/fromBasicDiagnosticTests/overloadsBound.kt");
            }

            @TestMetadata("postponedResolveOfManyCallableReference.kt")
            public void testPostponedResolveOfManyCallableReference() throws Exception {
                runTest("compiler/fir/resolve/testData/diagnostics/callableReferences/fromBasicDiagnosticTests/postponedResolveOfManyCallableReference.kt");
            }

            @TestMetadata("resolveCallableReferencesAfterAllSimpleArguments.kt")
            public void testResolveCallableReferencesAfterAllSimpleArguments() throws Exception {
                runTest("compiler/fir/resolve/testData/diagnostics/callableReferences/fromBasicDiagnosticTests/resolveCallableReferencesAfterAllSimpleArguments.kt");
            }

            @TestMetadata("withGenericFun.kt")
            public void testWithGenericFun() throws Exception {
                runTest("compiler/fir/resolve/testData/diagnostics/callableReferences/fromBasicDiagnosticTests/withGenericFun.kt");
            }
        }
    }

    @TestMetadata("compiler/fir/resolve/testData/diagnostics/j+k")
    @TestDataPath("$PROJECT_ROOT")
    @RunWith(JUnit3RunnerWithInners.class)
    public static class J_k extends AbstractFirDiagnosticsTest {
        private void runTest(String testDataFilePath) throws Exception {
            KotlinTestUtils.runTest(this::doTest, this, testDataFilePath);
        }

        public void testAllFilesPresentInJ_k() throws Exception {
            KotlinTestUtils.assertAllTestsPresentByMetadata(this.getClass(), new File("compiler/fir/resolve/testData/diagnostics/j+k"), Pattern.compile("^(.+)\\.kt$"), true);
        }

        @TestMetadata("complexFlexibleInference.kt")
        public void testComplexFlexibleInference() throws Exception {
            runTest("compiler/fir/resolve/testData/diagnostics/j+k/complexFlexibleInference.kt");
        }

        @TestMetadata("FieldAndGetter.kt")
        public void testFieldAndGetter() throws Exception {
            runTest("compiler/fir/resolve/testData/diagnostics/j+k/FieldAndGetter.kt");
        }

        @TestMetadata("FieldSubstitution.kt")
        public void testFieldSubstitution() throws Exception {
            runTest("compiler/fir/resolve/testData/diagnostics/j+k/FieldSubstitution.kt");
        }

        @TestMetadata("FunctionTypeInJava.kt")
        public void testFunctionTypeInJava() throws Exception {
            runTest("compiler/fir/resolve/testData/diagnostics/j+k/FunctionTypeInJava.kt");
        }

        @TestMetadata("KJKComplexHierarchy.kt")
        public void testKJKComplexHierarchy() throws Exception {
            runTest("compiler/fir/resolve/testData/diagnostics/j+k/KJKComplexHierarchy.kt");
        }

        @TestMetadata("KJKComplexHierarchyNestedLoop.kt")
        public void testKJKComplexHierarchyNestedLoop() throws Exception {
            runTest("compiler/fir/resolve/testData/diagnostics/j+k/KJKComplexHierarchyNestedLoop.kt");
        }

        @TestMetadata("KJKComplexHierarchyWithNested.kt")
        public void testKJKComplexHierarchyWithNested() throws Exception {
            runTest("compiler/fir/resolve/testData/diagnostics/j+k/KJKComplexHierarchyWithNested.kt");
        }

        @TestMetadata("KJKInheritance.kt")
        public void testKJKInheritance() throws Exception {
            runTest("compiler/fir/resolve/testData/diagnostics/j+k/KJKInheritance.kt");
        }

        @TestMetadata("KJKInheritanceGeneric.kt")
        public void testKJKInheritanceGeneric() throws Exception {
            runTest("compiler/fir/resolve/testData/diagnostics/j+k/KJKInheritanceGeneric.kt");
        }

        @TestMetadata("KotlinClassParameter.kt")
        public void testKotlinClassParameter() throws Exception {
            runTest("compiler/fir/resolve/testData/diagnostics/j+k/KotlinClassParameter.kt");
        }

        @TestMetadata("KotlinClassParameterGeneric.kt")
        public void testKotlinClassParameterGeneric() throws Exception {
            runTest("compiler/fir/resolve/testData/diagnostics/j+k/KotlinClassParameterGeneric.kt");
        }

        @TestMetadata("LoggerInstance.kt")
        public void testLoggerInstance() throws Exception {
            runTest("compiler/fir/resolve/testData/diagnostics/j+k/LoggerInstance.kt");
        }

        @TestMetadata("MyException.kt")
        public void testMyException() throws Exception {
            runTest("compiler/fir/resolve/testData/diagnostics/j+k/MyException.kt");
        }

        @TestMetadata("MyIterable.kt")
        public void testMyIterable() throws Exception {
            runTest("compiler/fir/resolve/testData/diagnostics/j+k/MyIterable.kt");
        }

        @TestMetadata("MyMap.kt")
        public void testMyMap() throws Exception {
            runTest("compiler/fir/resolve/testData/diagnostics/j+k/MyMap.kt");
        }

        @TestMetadata("outerInnerClasses.kt")
        public void testOuterInnerClasses() throws Exception {
            runTest("compiler/fir/resolve/testData/diagnostics/j+k/outerInnerClasses.kt");
        }

        @TestMetadata("RawType.kt")
        public void testRawType() throws Exception {
            runTest("compiler/fir/resolve/testData/diagnostics/j+k/RawType.kt");
        }
    }

    @TestMetadata("compiler/fir/resolve/testData/diagnostics/samConstructors")
    @TestDataPath("$PROJECT_ROOT")
    @RunWith(JUnit3RunnerWithInners.class)
    public static class SamConstructors extends AbstractFirDiagnosticsTest {
        private void runTest(String testDataFilePath) throws Exception {
            KotlinTestUtils.runTest(this::doTest, this, testDataFilePath);
        }

        public void testAllFilesPresentInSamConstructors() throws Exception {
            KotlinTestUtils.assertAllTestsPresentByMetadata(this.getClass(), new File("compiler/fir/resolve/testData/diagnostics/samConstructors"), Pattern.compile("^(.+)\\.kt$"), true);
        }

        @TestMetadata("genericSam.kt")
        public void testGenericSam() throws Exception {
            runTest("compiler/fir/resolve/testData/diagnostics/samConstructors/genericSam.kt");
        }

        @TestMetadata("genericSamInferenceFromExpectType.kt")
        public void testGenericSamInferenceFromExpectType() throws Exception {
            runTest("compiler/fir/resolve/testData/diagnostics/samConstructors/genericSamInferenceFromExpectType.kt");
        }

        @TestMetadata("kotlinSam.kt")
        public void testKotlinSam() throws Exception {
            runTest("compiler/fir/resolve/testData/diagnostics/samConstructors/kotlinSam.kt");
        }

        @TestMetadata("realConstructorFunction.kt")
        public void testRealConstructorFunction() throws Exception {
            runTest("compiler/fir/resolve/testData/diagnostics/samConstructors/realConstructorFunction.kt");
        }

        @TestMetadata("runnable.kt")
        public void testRunnable() throws Exception {
            runTest("compiler/fir/resolve/testData/diagnostics/samConstructors/runnable.kt");
        }

        @TestMetadata("simple.kt")
        public void testSimple() throws Exception {
            runTest("compiler/fir/resolve/testData/diagnostics/samConstructors/simple.kt");
        }
    }

    @TestMetadata("compiler/fir/resolve/testData/diagnostics/samConversions")
    @TestDataPath("$PROJECT_ROOT")
    @RunWith(JUnit3RunnerWithInners.class)
    public static class SamConversions extends AbstractFirDiagnosticsTest {
        private void runTest(String testDataFilePath) throws Exception {
            KotlinTestUtils.runTest(this::doTest, this, testDataFilePath);
        }

        public void testAllFilesPresentInSamConversions() throws Exception {
            KotlinTestUtils.assertAllTestsPresentByMetadata(this.getClass(), new File("compiler/fir/resolve/testData/diagnostics/samConversions"), Pattern.compile("^(.+)\\.kt$"), true);
        }

        @TestMetadata("genericSam.kt")
        public void testGenericSam() throws Exception {
            runTest("compiler/fir/resolve/testData/diagnostics/samConversions/genericSam.kt");
        }

        @TestMetadata("kotlinSam.kt")
        public void testKotlinSam() throws Exception {
            runTest("compiler/fir/resolve/testData/diagnostics/samConversions/kotlinSam.kt");
        }

        @TestMetadata("notSamBecauseOfSupertype.kt")
        public void testNotSamBecauseOfSupertype() throws Exception {
            runTest("compiler/fir/resolve/testData/diagnostics/samConversions/notSamBecauseOfSupertype.kt");
        }

        @TestMetadata("runnable.kt")
        public void testRunnable() throws Exception {
            runTest("compiler/fir/resolve/testData/diagnostics/samConversions/runnable.kt");
        }

        @TestMetadata("samSupertype.kt")
        public void testSamSupertype() throws Exception {
            runTest("compiler/fir/resolve/testData/diagnostics/samConversions/samSupertype.kt");
        }

        @TestMetadata("samSupertypeWithOverride.kt")
        public void testSamSupertypeWithOverride() throws Exception {
            runTest("compiler/fir/resolve/testData/diagnostics/samConversions/samSupertypeWithOverride.kt");
        }

        @TestMetadata("simple.kt")
        public void testSimple() throws Exception {
            runTest("compiler/fir/resolve/testData/diagnostics/samConversions/simple.kt");
        }
    }
}
