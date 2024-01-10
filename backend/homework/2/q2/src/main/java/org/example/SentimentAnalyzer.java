package org.example;
import java.util.*;

public class SentimentAnalyzer {
    private static LoggingSystem ls = new LoggingSystem();

    /**
     * it detects feature have positive / negative / no opinion using getOpinionOnFeature() Function
     * @param review review given by user
     * @param featureSet set of feature with possible variation of feature
     * @param posOpinionWords positive opinions words
     * @param negOpinionWords negative opinions words
     * @return array with 1 for feature having positive opinion
     *                    -1 for feature having negative opinion
     *                    0 for feature having no opinion
     */
    public static int[] detectProsAndCons(
            String review,
            String[][] featureSet,
            String[] posOpinionWords,
            String[] negOpinionWords
    ) {
        int features = featureSet.length;
        int[] featureOpinions = new int[features]; // output
        HashSet<String> reviewWordSet =new HashSet<>();
        addOpinionsToSet(reviewWordSet,splitIntoWords(review));
        for(int i = 0;i < features;i++)
        {
            for(String featureOption : featureSet[i])
            {
                if(reviewWordSet.contains(featureOption.toLowerCase()))
                {
                    featureOpinions[i] = getOpinionOnFeature(review,featureOption,posOpinionWords,negOpinionWords);
                    break;
                }
            }
        }
        return featureOpinions;
    }

    /**
     * get opinion using the checkForWasPhrasePattern() and checkForOpinionFirstPattern() functions
     * while giving preference to WasPhrasePattern first
     * @param review review given by user
     * @param feature feature we are looking for
     * @param posOpinionWords positive opinions words
     * @param negOpinionWords negative opinions words
     * @return opinion on feature
     */
    //// First invoke checkForWasPhrasePattern and
    //// if it cannot find an opinion only then invoke
    //    checkForOpinionFirstPattern
    private static int getOpinionOnFeature(
            String review,
            String feature,
            String[] posOpinionWords,
            String[] negOpinionWords
    ) {
        int opinion = checkForWasPhrasePattern(review,feature,posOpinionWords,negOpinionWords);
        if(opinion != 0)
        {
            return opinion;
        }
        opinion = checkForOpinionFirstPattern(review,feature,posOpinionWords,negOpinionWords);
        return opinion;
    }



    private static void addOpinionsToSet(HashSet<String> WordsSet,String[] opinionsWords)
    {
        for(String opinion : opinionsWords)
        {
            WordsSet.add(opinion.toLowerCase());
        }

    }

    private static boolean findOpinionForPhrasePattern(String[] reviewWords, String feature,HashSet<String> OpinionWordsSet) {
        for(int i  = 0;i < reviewWords.length;i++)
        {
            if(reviewWords[i].equalsIgnoreCase(feature) && reviewWords.length > i+2 && reviewWords[i+1].equalsIgnoreCase("was") && OpinionWordsSet.contains(reviewWords[i+2]))
            {
                    return true;
            }
        }
        return false;
    }

    /**
     * give opinion on feature of type {feature} + " was " + {opinion}
     * while giving preference to positive opinion
     * @param review review given by user
     * @param feature feature we are looking for
     * @param posOpinionWords positive opinions words
     * @param negOpinionWords negative opinions words
     * @return opinion on feature of WasPhrasePattern
     */
    //    // You can first look for positive opinion. If not found, only
    //    then you can look for negative opinion
    private static int checkForWasPhrasePattern(
            String review,
            String feature,
            String[] posOpinionWords,
            String[] negOpinionWords
    ) {
        int opinion = 0;
        HashSet<String> posOpinionWordsSet = new HashSet<>();
        HashSet<String> negOpinionWordsSet = new HashSet<>();
        addOpinionsToSet(posOpinionWordsSet,posOpinionWords);
        addOpinionsToSet(negOpinionWordsSet,negOpinionWords);

        String[] reviewWords = splitIntoWords(review);

        if(findOpinionForPhrasePattern(reviewWords,feature,posOpinionWordsSet)) return 1;
        if(findOpinionForPhrasePattern(reviewWords,feature,negOpinionWordsSet)) return -1;
        return opinion;
    }

    // You can first look for positive opinion. If not found, only then you can look for negative opinion
    private static String[] splitIntoWords(String str)
    {
        return str.split("[^a-zA-Z']+");
    }

    private static boolean findOpinionFirstPattern(String[] sentenceWords,String feature,HashSet<String> OpinionWordsSet){
        for(int i = 0;i < sentenceWords.length;i++)
        {
            if(sentenceWords[i].equalsIgnoreCase(feature) && i > 0 && OpinionWordsSet.contains(sentenceWords[i-1].toLowerCase()))
            {
                    return true;
            }
        }
        return false;
    }
    /**
     * give opinion on feature of type {opinion} {feature}
     * while giving preference to positive opinion
     * @param review review given by user
     * @param feature feature we are looking for
     * @param posOpinionWords positive opinions words
     * @param negOpinionWords negative opinions words
     * @return opinion on feature of OpinionFirstPattern
     */
    private static int checkForOpinionFirstPattern(
            String review,
            String feature,
            String[] posOpinionWords,
            String[] negOpinionWords
    ) {

        String[] sentences = review.split("\\.");
        int opinion = 0;

        HashSet<String> posOpinionWordsSet = new HashSet<>();
        HashSet<String> negOpinionWordsSet = new HashSet<>();
        addOpinionsToSet(posOpinionWordsSet,posOpinionWords);
        addOpinionsToSet(negOpinionWordsSet,negOpinionWords);

        for(String sentence : sentences){

            String[] sentenceWords = splitIntoWords(sentence);
            // check for positive opinions
            if(findOpinionFirstPattern(sentenceWords,feature,posOpinionWordsSet)) return 1;
            if(findOpinionFirstPattern(sentenceWords,feature,negOpinionWordsSet)) return -1;
            // not found then for negative opinions
        }
        return opinion;
    }

    public static void main(String[] args) {
        String review1 = "Haven't been here in years! Fantastic service and the food was delicious! Definetly will be a frequent flyer! Francisco was very attentive";
        String review2 = "Sorry OG, but you just lost some loyal customers. Horrible service, no smile or greeting just attitude. The breadsticks were stale and burnt, appetizer was cold and the food came out before the salad.";
        String[][] featureSet = {
                { "ambiance", "ambience", "atmosphere", "decor" },
                { "dessert", "ice cream", "desert" },
                { "food" },
                { "soup" },
                {
                        "service",
                        "management",
                        "waiter",
                        "waitress",
                        "bartender",
                        "staff",
                        "server",
                },
        };
        String[] posOpinionWords = {
                "good",
                "fantastic",
                "friendly",
                "great",
                "excellent",
                "amazing",
                "awesome",
                "delicious",
        };
        String[] negOpinionWords = {
                "slow",
                "bad",
                "horrible",
                "awful",
                "unprofessional",
                "poor",
        };
        int[] featureOpinionsForReview1 = detectProsAndCons(
                review1,
                featureSet,
                posOpinionWords,
                negOpinionWords
        );
        int[] featureOpinionsForReview2 = detectProsAndCons(
                review1,
                featureSet,
                posOpinionWords,
                negOpinionWords
        );

        ls.logInfo(
                "Opinions on Features: " + Arrays.toString(featureOpinionsForReview1)
        );
        ls.logInfo(
                "Opinions on Features: " + Arrays.toString(featureOpinionsForReview2)
        );
    }
}
