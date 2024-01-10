package org.example;

public class APIResponseParser {
    /**
     * Parses the input text and returns a Book instance containing
     * the parsed data.
     * @param response text to be parsed
     * @return Book instance containing parsed data
     */
    public static Book parse(String response) {
        Book book = new Book();

        String endRule = "</title>";
        String startRule = "<title>";
        String title = parse(response, startRule, endRule);
        book.setTitle(title);

        startRule = "<original_publication_year type=\"integer\">";
        endRule = "</original_publication_year>";
        String publicationYear = parse(response, startRule, endRule);
        book.setPublicationYear(Integer.parseInt(publicationYear));

        startRule = "<ratings_count type=\"integer\">";
        endRule = "</ratings_count>";
        String ratingCount = parse(response, startRule, endRule);
        book.setRatingsCount(Integer.parseInt(avoidCommas(ratingCount)));

        startRule = "<average_rating>";
        endRule = "</average_rating>";
        String averageRating = parse(response, startRule, endRule);
        book.setAverageRating(Double.parseDouble(averageRating));

         endRule = "</name>";
         startRule = "<name>";
         String author = parse(response, startRule, endRule);
         book.setAuthor(author);

        endRule = "</image_url>";
        startRule = "<image_url>";
        String imageUrl = parse(response, startRule, endRule);
        book.setImageUrl(imageUrl);

        return book;
    }

    private static String avoidCommas(String str)
    {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < str.length(); i++)
        {
            if(str.charAt(i) != ','){
                sb.append(str.charAt(i));
            }
        }
        return sb.toString();
    }
    public static String parse(String response,String startRule,String endRule)
    {
        int startIndex = response.indexOf(startRule);
        startIndex += startRule.length();
        int endIndex = response.indexOf(endRule);
        return response.substring(startIndex,endIndex);
    }
    public static void main(String[] args) {
        String response = "<work>" +
                "<id type=\"integer\">2361393</id>" +
                "<books_count type=\"integer\">813</books_count>" +
                "<ratings_count type=\"integer\">1,16,315</ratings_count>" +
                "<text_reviews_count type=\"integer\">3439</text_reviews_count>" +
                "<original_publication_year type=\"integer\">1854</original_publication_year>" +
                "<original_publication_month type=\"integer\" nil=\"true\"/>" +
                "<original_publication_day type=\"integer\" nil=\"true\"/>" +
                "<average_rating>3.79</average_rating>" +
                "<best_book type=\"Book\">" +
                "<id type=\"integer\">16902</id>" +
                "<title>Walden</title>" +
                "<author>" +
                "<id type=\"integer\">10264</id>" +
                "<name>Henry David Thoreau</name>" +
        "</author>" +
                "<image_url>" +
                "http://images.gr-assets.com/books/1465675526m/16902.jpg" +
                "</image_url>" +
                "<small_image_url>" +
                "http://images.gr-assets.com/books/1465675526s/16902.jpg" +
                "</small_image_url>" +
                "</best_book>" +
                "</work>";

        Book  book = APIResponseParser.parse(response);
        book.printBook();
    }
}

