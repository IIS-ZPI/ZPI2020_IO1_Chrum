import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnitTests {

    @Test
    void getMedianTest(){
        double[] set1 = {1.0 , 1.0 , 1.0 , 671.0 , 876.0 , 11.33 , 121.2 , 1.111 , 1.2 , 1.4 , 2.3};

        double answer  = AnalysisData.getMedian(set1);

        assertEquals(answer, 1.4);

        double[] set2 = {1.0 , 1.0 , 1.1 , 1.2};

        answer  = AnalysisData.getMedian(set2);
        assertEquals(answer, 1.05);


    }
    @Test
    void getDominantTest(){
        double[] testingSet1 = {0,1,2,3,4,5,6,7,8,9,0};
        assertEquals(AnalysisData.getDominant(testingSet1), 0);

        double[] testingSet2 = {0.0,1,2,3,4,5,6,7,8,9,0};
        assertEquals(AnalysisData.getDominant(testingSet2), 0);

        double[] testingSet3 = {0.0,1,2,3,4.0,5,4.0,6,7, 4.0,8,9,0};
        assertEquals(AnalysisData.getDominant(testingSet3), 4.0);
        assertEquals(AnalysisData.getDominant(testingSet3), 4);

    }

    @Test
    void getStandardDeviationTest() {

        double[] testingSet1 = {0,1,2,3,4,5,6,7,8,9,0};
        assertTrue(Math.abs(AnalysisData.getStandardDeviation(testingSet1) - 3.02878) < 0.00001);

        double[] testingSet2 = {1.0 , 1.0 , 1.0 , 671.0 , 876.0 , 11.33 , 121.2 , 1.111 , 1.2 , 1.4 , 2.3};
        assertTrue(Math.abs(AnalysisData.getStandardDeviation(testingSet2) - 297.45135) < 0.00001);
    }

    @Test
    void getCoefficientOfVariationTest() {
        double[] testingSet1 = {0,1,2,3,4,5,6,7,8,9,0};
        assertTrue(Math.abs(AnalysisData.coefficientOfVariation(testingSet1) - 0.74037) < 0.00001);

        double[] testingSet2 = {1.0 , 1.0 , 1.0 , 671.0 , 876.0 , 11.33 , 121.2 , 1.111 , 1.2 , 1.4 , 2.3};
        assertTrue(Math.abs(AnalysisData.coefficientOfVariation(testingSet2) - 1.93774) < 0.00001);

    }

    @Test
    void upSessionTest() {

        double[] testingSet1 = {0,1,2,3,4,5,6,7,8,9,0};
        assertEquals(AnalysisData.upSession(testingSet1), 1);


        double[] testingSet2 = {1.0 , 1.0 , 1.0 , 671.0 , 876.0 , 11.33 , 121.2 , 1.111 , 1.2 , 1.4 , 2.3};
        assertEquals(AnalysisData.upSession(testingSet2), 3);

    }

    @Test
    void downSessionTest(){
        double[] testingSet1 = {0,1,2,3,4,5,6,7,8,9,0};
        double[] testingSet2 = {1.0 , 1.0 , 1.0 , 671.0 , 876.0 , 11.33 , 121.2 , 1.111 , 1.2 , 1.4 , 2.3};
        double[] testingSet3 = {1,1,1,1,1,1,1,1,1};

        assertEquals(AnalysisData.downSession(testingSet1), 1);
        assertEquals(AnalysisData.downSession(testingSet2), 2);
        assertEquals(AnalysisData.downSession(testingSet3), 0);

    }

    @Test
    void stableSessionTest () {
        double[] testingSet1 = {0,1,2,3,4,5,6,7,8,9,0};
        double[] testingSet2 = {1.0 , 1.0 , 1.0 , 671.0 , 876.0 , 11.33 , 121.2 , 1.111 , 1.2 , 1.4 , 2.3};
        double[] testingSet3 = {1,1,1,1,1,1,1,1,1};

        assertEquals(AnalysisData.stableSession(testingSet1), 0);
        assertEquals(AnalysisData.stableSession(testingSet2), 1);
        assertEquals(AnalysisData.stableSession(testingSet3), 1);
    }

    @Test
    void getCurrencyTest(){
        double[] usdCurrencyFor3rd = {3.7878,3.8132,3.8214,3.8198,3.7931};
        try {
            double[] answer = Parser.getCurrency("USD", "week");

        for (int i = 0; i < 5; i++){
            assert answer != null;
            assertEquals(answer[i], usdCurrencyFor3rd[i]);
        }

        assertNull(Parser.getCurrency("USD", "weed"));
        double[] testValue = Parser.getCurrency("weeed", "week");

        assert testValue != null;

        assertEquals(0, testValue.length);
        assertNull(Parser.getCurrency("weasdsadeed", "weesadasdk"));

        assertNotNull(Parser.getCurrency("USD", "week"));
        assertNotNull(Parser.getCurrency("USD", "two weeks"));
        assertNotNull(Parser.getCurrency("USD", "quarter"));
        assertNotNull(Parser.getCurrency("USD", "year"));

        assertNotNull(Parser.getCurrency("USD", "month"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
