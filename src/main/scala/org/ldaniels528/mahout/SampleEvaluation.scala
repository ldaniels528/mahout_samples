package org.ldaniels528.mahout

import java.io.File

import org.apache.mahout.cf.taste.eval.RecommenderBuilder
import org.apache.mahout.cf.taste.impl.eval.AverageAbsoluteDifferenceRecommenderEvaluator
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity
import org.apache.mahout.cf.taste.model.DataModel
import org.slf4j.LoggerFactory

/**
 * Sample Evaluation
 * @author lawrence.daniels@gmail.com
 */
object SampleEvaluation {
  private val logger = LoggerFactory.getLogger(getClass)

  /**
   * Main application entry point
   * @param args the given command line arguments
   */
  def main(args: Array[String]) {

    /*
     * You might ask yourself, how to make sure that your recommender returns good results.
     * Unfortunately, the only way to be really sure about the quality is by doing an A/B
     * test with real users in a live system.
     *
     * We can however try to get a feel of the quality, by statistical offline evaluation.
     * Just keep in mind that this does not replace a test with real users! One way to check
     * whether the recommender returns good results is by doing a hold-out test. We partition
     * our dataset into two sets: a trainingset consisting of 90% of the data and a testset
     * consisting of 10%. Then we train our recommender using the training set and look how
     * well it predicts the unknown interactions in the testset.
     *
     * To test our recommender, we create a class called EvaluateRecommender with a main method
     * and add an inner class called MyRecommenderBuilder that implements the RecommenderBuilder
     * interface. We implement the buildRecommender method and make it setup our user-based
     * recommender:
     */
    val dataModel = new FileDataModel(new File("./src/main/resources/dataset.csv"))
    val evaluator = new AverageAbsoluteDifferenceRecommenderEvaluator()
    val builder = new MyRecommenderBuilder()
    val result = evaluator.evaluate(builder, null, dataModel, 0.9, 1.0)
    logger.info(s"result: $result")
  }

  class MyRecommenderBuilder extends RecommenderBuilder {
    override def buildRecommender(dataModel: DataModel) = {
      val similarity = new PearsonCorrelationSimilarity(dataModel)
      val neighborhood = new ThresholdUserNeighborhood(0.1, similarity, dataModel)
      new GenericUserBasedRecommender(dataModel, neighborhood, similarity)
    }
  }

}
