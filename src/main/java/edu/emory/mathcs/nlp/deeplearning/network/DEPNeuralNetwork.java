package edu.emory.mathcs.nlp.deeplearning.network;

import edu.emory.mathcs.nlp.deeplearning.activation.CubeFunction;
import edu.emory.mathcs.nlp.deeplearning.activation.SigmoidFunction;

/**
 * Created by bong on 10/6/15.
 */
public class DEPNeuralNetwork extends FeedForwardNeuralNetwork{
    public DEPNeuralNetwork(double learningRate, int input, int output, int hidden, boolean is_cube)
    {
        super(learningRate, input, output, hidden);
        if (is_cube)
            activation_function = new CubeFunction();
        else
            activation_function = new SigmoidFunction();
        learning_rate = learningRate;
        init(input, output, hidden);
    }
}
