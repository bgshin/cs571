/**
 * Copyright 2015, Emory University
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package edu.emory.mathcs.nlp.deeplearning.activation;

import edu.emory.mathcs.nlp.common.util.Sigmoid;

/**
 * Created by bong on 10/6/15.
 */
public class CubeFunction implements ActivationFunction{

    private Cube table;

    /** Calls {@link #CubeFunction(int, float, float)}, where size = 3500, floor = -6, ceiling = 6. */
    public CubeFunction()
    {
//        table = new Cube();
    }

    @Override
    public void transform(double[] scores)
    {
        for (int i=0; i<scores.length; i++) {
            double raw_val = (scores[i]*scores[i]*scores[i]);
//            double raw_val = get(scores[i]);
            if (raw_val>1)
                scores[i] = 1;
            else if (raw_val<-1)
                scores[i] = -1;
            else
                scores[i] = raw_val;
        }

    }

    public final double get(double d)
    {
        return table.get(d);
    }


    private class Cube {
        private final float[] table;
        private final float floor;
        private final float ceiling;
        private final float table_multiply;
        private final int table_adjust;

        public Cube() {
            this(3500, -6.0F, 6.0F);
        }

        public Cube(int size, float floor, float ceiling) {
            this.floor = floor;
            this.ceiling = ceiling;
            this.table = new float[size];
            float range = ceiling - floor;
            this.table_adjust = (int)(0.5D - (double)(floor * (float)(size - 1) / range));
            this.table_multiply = (float)(size - 1) / range;

            for(int i = 0; i < size; ++i) {
                float x=(float)(1.0D + Math.exp(6.0D * ((double)(floor + ceiling) - 2.0D * (double)(floor + range * (float)i / (float)(size - 1))) / (double)range));
                this.table[i] =x*x*x;
            }
        }

        public final double get(double d) {
            return d <= (double)this.floor?0.0D:(d >= (double)this.ceiling?1.0D:(double)this.table[(int)(d * (double)this.table_multiply) + this.table_adjust]);
        }
    }

}


