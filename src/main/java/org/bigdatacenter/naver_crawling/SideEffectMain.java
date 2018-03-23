package org.bigdatacenter.naver_crawling;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.bigdatacenter.naver_crawling.models.SideEffect;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by HP1 on 10/13/2017.
 */
public class SideEffectMain {
    private static final String COMMA_DELIMITER =  ",";
    private static final String NEW_LINE_SEPARATER = "\n";

    public static void main(String args[]) throws IOException {

        Gson gson = new Gson();
        JsonReader reader = new JsonReader(new FileReader("se.json"));

        SideEffect[] sideEffects = gson.fromJson(reader, SideEffect[].class);

        //TODO: TO CREATE BufferedWriter FOR WRITTING TO NEW CSV FILE
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("siders.csv"));
        bufferedWriter.append("Sider");
        bufferedWriter.append(NEW_LINE_SEPARATER);
        for(SideEffect sideEffect: sideEffects){
            //System.out.println(sideEffect.getSider());
            try {
                Document document = Jsoup.connect(sideEffect.getSider()).get();
            }catch(Exception ex){
                System.out.println(sideEffect.getSider());
                bufferedWriter.append(sideEffect.getSider());
                bufferedWriter.append(NEW_LINE_SEPARATER);
            }
        }
        //TODO: TO SAVE
        bufferedWriter.flush();
        bufferedWriter.close();
    }
}