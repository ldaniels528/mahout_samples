package org.ldaniels528.mahout

import java.io.File

import org.apache.mahout.cf.taste.impl.model.file.FileDataModel
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity
import org.slf4j.LoggerFactory

import scala.collection.JavaConversions._

/**
 * Sample Recommendation
 * @author lawrence.daniels@gmail.com
 */
object SampleRecommendation {
  private val logger = LoggerFactory.getLogger(getClass)

  /**
   * Main application entry point
   * @param args the given command line arguments
   */
  def main(args: Array[String]) {

    /*
     * The first thing we have to do is load the data from the file.
     * Mahout's recommenders use an interface called DataModel to handle interaction data.
     * You can load our made up interactions like this:
     */
    val model = new FileDataModel(new File("./src/main/resources/dataset.csv"))

    /*
     * In this example, we want to create a user-based recommendation engine. The idea behind this approach is that
     * when we want to compute recommendations for a particular users, we look for other users with a similar
     * taste and pick the recommendations from their items. For finding similar users, we have to compare
     * their interactions. There are several methods for doing this. One popular method is to compute
     * the correlation coefficient between their interactions. In Mahout, you use this method as follows:
     */
    val similarity = new PearsonCorrelationSimilarity(model)

    /*
     * The next thing we have to do is to define which similar users we want to leverage for the recommendation engine.
     * For the sake of simplicity, we'll use all that have a similarity greater than 0.1. This is implemented
     * via a ThresholdUserNeighborhood:
     */
    val neighborhood = new ThresholdUserNeighborhood(0.1, similarity, model)

    /*
     * Now we have all the pieces to create our recommendation engine:
     */
    val recommender = new GenericUserBasedRecommender(model, neighborhood, similarity)

    /*
     * We can easily ask the recommendation engine for recommendations now. If we wanted to get
     * three items recommended for the user with userID 2, we would do it like this:
     */
    for (recommendation <- recommender.recommend(2, 3)) {
      logger.info(s"recommendation: $recommendation")
    }
  }

}
