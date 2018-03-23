package org.bigdatacenter.naver_crawling;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.bigdatacenter.naver_crawling.models.*;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by PENHCHET on 9/29/2017.
 */
public class DrugsMain {

    private static final String COMMA_DELIMITER =  ",";
    private static final String NEW_LINE_SEPARATER = "\n";

    public static void main(String args[]) throws IOException {

        Gson gson = new Gson();
        JsonReader reader = new JsonReader(new FileReader("drugs.json"));

        Drug[] drugs = gson.fromJson(reader, Drug[].class);

        List<BaseDrug> baseDrugs = new ArrayList<>();
        //TODO: TO CREATE BufferedWriter FOR WRITTING TO NEW CSV FILE
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("drugs.csv"));
        bufferedWriter.append("Key,Title,Side Effects,% of Side Effects, Labels,% of Labels,ParentKey,Url");
        bufferedWriter.append(NEW_LINE_SEPARATER);
        for(Drug drug: drugs){
            drug.setStatistic(bufferedWriter);
            System.out.println(drug);
            List<FirstChildren> children = drug.getChildren();
            baseDrugs.add(drug);
//            bufferedWriter.append(drug.getKey());
//            bufferedWriter.append(COMMA_DELIMITER);
//            bufferedWriter.append(drug.getTitle());
//            bufferedWriter.append(COMMA_DELIMITER);
//            bufferedWriter.append(drug.getTotalSideEffects() + "");
//            bufferedWriter.append(COMMA_DELIMITER);
//            bufferedWriter.append(drug.getTotalSideEffectsPercentage() + "");
//            bufferedWriter.append(COMMA_DELIMITER);
//            bufferedWriter.append(drug.getTotalLabels() + "");
//            bufferedWriter.append(COMMA_DELIMITER);
//            bufferedWriter.append(drug.getTotalLabelsPercentage() + "");
//            bufferedWriter.append(COMMA_DELIMITER);
//            bufferedWriter.append(drug.getParentKey());
//            bufferedWriter.append(COMMA_DELIMITER);
//            bufferedWriter.append(NEW_LINE_SEPARATER);
            for(FirstChildren firstChildren: children){
                firstChildren.setParentKey(drug.getKey());
                firstChildren.setStatistic(bufferedWriter);
                baseDrugs.add(firstChildren);
                System.out.println("=> " + firstChildren.getKey() + " -- " + firstChildren.getTitle());
                for(SecondChildren secondChildren: firstChildren.getChildren()){
                    secondChildren.setParentKey(firstChildren.getKey());
                    secondChildren.setStatistic(bufferedWriter);
                    baseDrugs.add(secondChildren);
                    System.out.println("==> " + secondChildren.getKey() + " -- " + secondChildren.getTitle());
                    for(ThirdChildren thirdChildren: secondChildren.getChildren()){
                        thirdChildren.setParentKey(secondChildren.getKey());
                        thirdChildren.setStatistic(bufferedWriter);
                        baseDrugs.add(thirdChildren);
                        System.out.println("===> " + thirdChildren.getKey() + " -- " + thirdChildren.getTitle());
                        for(FourthChildren fourthChildren: thirdChildren.getChildren()){
                            fourthChildren.setParentKey(thirdChildren.getKey());
                            fourthChildren.setStatistic(bufferedWriter);
                            baseDrugs.add(fourthChildren);
                            System.out.println("====> " + fourthChildren.getKey() + " -- " + fourthChildren.getTitle());
                        }
                    }
                }
            }
        }
        //TODO: TO SAVE
        bufferedWriter.flush();
        bufferedWriter.close();
    }
}