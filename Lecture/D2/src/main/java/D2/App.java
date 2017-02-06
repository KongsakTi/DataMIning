package D2;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App
{
    static int START_IDX = 30;
    static int END_IDX = 40;
    static List<String> MATCH_IDS = new ArrayList<String>();
    public static void main( String[] args ) throws Exception
    {
        int page = 1;
        String headUrl = "https://www.dotabuff.com/esports/matches?page=";

        while (MATCH_IDS.size() < 1000) {
            String url = headUrl + page;
            Document doc = Jsoup.connect(url).get();
            Elements htmls = doc.select("[href]");
            for (Element html : htmls) {
                String outerHtml = html.outerHtml();
                if (outerHtml.matches(".+/matches/.+")) {
                    MATCH_IDS.add(outerHtml.substring(START_IDX, END_IDX));
                }
            }
            page++;
            System.out.println(page + " " + MATCH_IDS.size());
        }
        File file = new File("./Matches");
        FileUtils.writeStringToFile(file, MATCH_IDS.toString());
        System.out.println("Done");
    }
}
