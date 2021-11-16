import java.util.List;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.types.Comment;
import com.restfb.types.GraphResponse;
import com.restfb.types.Post;

public class Main {

  public static void main(String[] args) {
    String accessToken = "\n" +
"EAAdaW1VZBg3sBAI39D4MnJ4LAcZCTR4RlSqItf5cVTfJPyWsiLbYmhUC2IwCsCX7eZC3D2MBvZBoPyoMqWCiKeWZAUgwU71dZCtYwNiiRIWAcX2ZCKcBut3pm5T7A6KHh8T7k2ZAxMNOlRaUSODMapm2ojQ73sst4tLu5yTvGckm3O3Tj0lq1IvkWqoXpIxofScykD3zl8SR9QZDZD";
    FacebookClient facebookClient = new DefaultFacebookClient(accessToken, Version.LATEST);
//    Page page = facebookClient.fetchObject("me", Page.class);
    String messageText = "Just a simple comment little comment";

    GraphResponse response =
        facebookClient.publish("108509604976277_109353284891909/comments", GraphResponse.class,
            Parameter.with("message", messageText));
    System.out.println(response.toString());

  }

  public void getComments(FacebookClient facebookClient) {
    String postId = "108509604976277_109353284891909"; // 108505421643362_108509604976277 108509604976277_108591291634775   108509604976277_109353284891909
    Connection<Comment> commentConnection
        = facebookClient.fetchConnection(postId + "/comments",
        Comment.class, Parameter.with("limit", 10));

    int personalLimit = 50;

    for (List<Comment> commentPage : commentConnection) { // 153282273623997_153296733622551
      for (Comment comment : commentPage) {
        System.out.println("Id: " + comment.getId());
        personalLimit--;

        // break both loops
        if (personalLimit == 0) {
          return;
        }
      }
    }
  }

//  public static void webhook() {
//    String pushedJsonAsString = "";
//    JsonMapper mapper = new DefaultJsonMapper();
//    WebhookObject webhookObject =
//        mapper.toJavaObject(pushedJsonAsString, WebhookObject.class);
//
//    Webhook webhook = new Webhook();
//    webhook.registerListener(new MyFeedCommentListener());
//    webhook.process(webhookObject);
//  }

  public static void fetchPosts(FacebookClient facebookClient) {
    Connection<Post> myFeed = facebookClient.fetchConnection("me/feed", Post.class);
    for (List<Post> myFeedPage : myFeed) {
      for (Post post : myFeedPage) {
        System.out.println("Post: " + post);
      }
    }

    // 102183482067210 - page id
  }
}
