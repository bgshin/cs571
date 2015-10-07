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

    /** Calls {@link #CubeFunction(int, float, float)}, where size = 3500, floor = -6, ceiling = 6. */
    public CubeFunction()
    {
    }

    @Override
    public void transform(double[] scores)
    {
        for (int i=0; i<scores.length; i++) {
            double raw_val = (scores[i]*scores[i]*scores[i]);
            if (raw_val>1)
                scores[i] = 1;
            else if (raw_val<-1)
                scores[i] = -1;
            else
                scores[i] = raw_val;
        }

    }
}
