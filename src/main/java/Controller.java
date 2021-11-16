import com.restfb.DefaultJsonMapper;
import com.restfb.JsonMapper;
import com.restfb.types.webhook.WebhookObject;
import com.restfb.webhook.Webhook;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/facebook")
public class Controller {
  @GetMapping
  public ResponseEntity<String> verifyWebhook(
      @RequestParam("hub.mode") String mode,
      @RequestParam("hub.verify_token") String verifyToken,
      @RequestParam("hub.challenge") final String challenge) {
    System.out.println("Mode: " + mode);
    System.out.println("VerifyToken " + verifyToken);
    return ResponseEntity.ok(challenge);
    // ngrok http http://local.cusbo.tech:8081
  }

  @PostMapping
  public ResponseEntity<Void> handleCallback(@RequestBody String payload,
                                             @RequestHeader("X-Hub-Signature") String signature) {
    System.out.println(String.format("Received Messenger Platform callback - payload: %s | signature: %s",
        payload, signature));

    JsonMapper mapper = new DefaultJsonMapper();
    WebhookObject webhookObject =
        mapper.toJavaObject(payload, WebhookObject.class);

    Webhook webhook = new Webhook();
    webhook.registerListener(new MyFeedCommentListener());
    webhook.process(webhookObject);
    return ResponseEntity.status(HttpStatus.OK).build();
  }
}
