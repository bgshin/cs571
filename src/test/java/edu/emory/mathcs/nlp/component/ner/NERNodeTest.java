package edu.emory.mathcs.nlp.component.ner;

import edu.emory.mathcs.nlp.component.ner.NERNode;
import edu.emory.mathcs.nlp.component.util.node.FeatMap;
import org.junit.Test;

import java.io.FileInputStream;

import static org.junit.Assert.assertEquals;

/**
 * Created by bong on 10/14/15.
 */
public class NERNodeTest {
    @Test
//	@Ignore
    public void test() throws Exception {
        NERNode[] sys = new NERNode[11];
        NERNode[] gold = new NERNode[9];

        FeatMap m =new FeatMap();

        gold[0] = new NERNode(0,"AAA1 BBB1", "aaa1 bbb1", "NNP", m, "Person");
        gold[1] = new NERNode(1,"AAA2", "aaa2", "NNP", m, "");
        gold[2] = new NERNode(2,"AAA3 BBB2", "aaa3 bbb2", "NNP", m, "Company");
        gold[3] = new NERNode(3,"AAA4", "aaa4", "NNP", m, "");
        gold[4] = new NERNode(4,"AAA5", "aaa5", "NNP", m, "");
        gold[5] = new NERNode(5,"AAA6", "aaa6", "NNP", m, "");
        gold[6] = new NERNode(6,"AAA7", "aaa7", "NNP", m, "");
        gold[7] = new NERNode(7,"AAA8 BBB3", "aaa8 bbb3", "NNP", m, "Location");
        gold[8] = new NERNode(8,"AAA9", "aaa9", "NNP", m, "");


        sys[0] = new NERNode(0,"AAA1 BBB1", "aaa1 bbb1", "NNP", m, "Person");
        sys[1] = new NERNode(1,"AAA2", "aaa2", "NNP", m, "Person");
        sys[2] = new NERNode(2,"AAA3", "aaa3", "NNP", m, "Company");
        sys[3] = new NERNode(3,"BBB2", "bbb2", "NNP", m, "");
        sys[4] = new NERNode(4,"AAA4", "aaa4", "NNP", m, "");
        sys[5] = new NERNode(5,"AAA5", "aaa5", "NNP", m, "");
        sys[6] = new NERNode(6,"AAA6", "aaa6", "NNP", m, "");
        sys[7] = new NERNode(7,"AAA7", "aaa7", "NNP", m, "");
        sys[8] = new NERNode(8,"AAA8", "aaa8", "NNP", m, "");
        sys[9] = new NERNode(9,"BBB3", "bbb3", "NNP", m, "");
        sys[10] = new NERNode(10,"AAA9", "aaa9", "NNP", m, "Location");

        System.out.println(isSame(gold[0], sys[0]));
        assertEquals(getF1(gold, sys), 0.14285714285714288, 0.00000001);
    }

    public double getF1(NERNode[] gold, NERNode[] sys) {
        double True_Positives=0;
        double False_Positives=0;
        double False_Negatives=0;


        int i,j; // i for gold, j for sys

        // find exact match (True_Positives)
        for (i=0; i<gold.length; i++) {
            String gold_i = gold[i].getNamedEntityTag();
            if (gold_i.length()==0)
                continue;

            boolean is_gold_found=false;
            for (j=0; j<sys.length; j++) {
                String sys_j = sys[j].getNamedEntityTag();
                if (sys_j.length()==0)
                    continue;

                if (gold_i.compareTo(sys_j) == 0 && isSame(gold[i], sys[j])) {
                    // sys predicts correctly
                    True_Positives++;
                    is_gold_found=true;
                }
            }

            if (is_gold_found==false)
                False_Negatives++;// sys doesn't predict
        }

        // find False_Positives
        for (j=0; j<sys.length; j++) {
            String sys_j = sys[j].getNamedEntityTag();
            if (sys_j.length()==0)
                continue;

            False_Positives++;
            for (i=0; i<gold.length; i++) {
                String gold_i = gold[i].getNamedEntityTag();
                if (gold_i.length()==0)
                    continue;
                if (gold_i.compareTo(sys_j) == 0 && isSame(gold[i], sys[j])) {
                    // sys predicts correctly
                    False_Positives--;
                }
            }
        }

        System.out.println(True_Positives);
        System.out.println(False_Negatives);
        System.out.println(False_Positives);


        double Precision = True_Positives / (True_Positives + False_Positives);
        double Recall = True_Positives / (True_Positives + False_Negatives);
        double f1 = Precision*Recall/(Precision+Recall);

        System.out.println(Precision);
        System.out.println(Recall);
        System.out.println(f1);


        return f1;
    }

    public boolean isSame(NERNode a, NERNode b) {
        boolean c2=a.isLemma(b.getLemma());
        boolean c3=a.isPOSTag(b.getPOSTag());

        return c2&&c3;
    }
}
