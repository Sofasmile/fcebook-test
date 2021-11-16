import com.restfb.types.webhook.FeedCommentValue;
import com.restfb.webhook.AbstractWebhookChangeListener;

class MyFeedCommentListener extends AbstractWebhookChangeListener {
    @Override
    public void feedCommentValue(FeedCommentValue feedCommentValue) {
     // do some stuff with the FeedCommentValue here
        System.out.println(feedCommentValue);
    }
}
