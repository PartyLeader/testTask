package sk.constants;

import sk.helpers.RandomHelper;
import sk.models.Article;

import static com.google.common.collect.Lists.newArrayList;

public class ArticleConstants {

    public static Article NEW_ARTICLE() {
        return new Article("How to Test Api", "Api Testing",
                "When I get a test case, the first thing I do is to analyze the requirements and figure out how to test the acceptance criteria in different ways. Then, I write manual test cases on Jira and do test executions",
                newArrayList("application"));
    }

    public static Article RANDOM_ARTICLE() {
        return new Article(RandomHelper.randomString(), RandomHelper.randomString(),
                RandomHelper.randomString(50), newArrayList("application"));
    }
}
