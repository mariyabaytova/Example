package Entity;

import com.example.HW55.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/rest/api/2/issue")
public class Controller {
    private final Logger logger = LoggerFactory.getLogger(Controller.class);

    private final Map<String, Task> issues = new HashMap<>();

    @GetMapping("/{issueKey}")
    public ResponseEntity<Task> getIssue(@PathVariable String issueKey) {
        logger.info("Getting issue with key: {}", issueKey);
        if (issues.containsKey(issueKey)) {
            Task issue = issues.get(issueKey);
            logger.info("Task found: {}", issue);
            return ResponseEntity.ok(issue);
        } else {
            logger.warn("Issue with key {} not found", issueKey);
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<response> createIssue(@RequestBody Map<String, Object> requestBody) {
        String projectKey = (String) ((Map<String, Object>) ((Map<String, Object>) requestBody.get("fields")).get("project")).get("key");
        String summary = (String) ((Map<String, Object>) requestBody.get("fields")).get("summary");
        String description = (String) ((Map<String, Object>) requestBody.get("fields")).get("description");
        String issueType = (String) ((Map<String, Object>) ((Map<String, Object>) requestBody.get("fields")).get("issuetype")).get("name");

        if (projectKey != null && summary != null && description != null && issueType != null) {
            Task issue = new Task(projectKey, summary, description, issueType);
            logger.info("Creating issue: {}", issue);
            String id = String.valueOf(issues.size() + 1);
            String issueKey = projectKey + "-" + id;
            issue.setId(id);
            issue.setKey(issueKey);
            issue.setSelf("http://localhost:8080/rest/api/2/issue/" + id);
            issues.put(id, issue);
            logger.info("Issue created with key: {}", issueKey);
            return ResponseEntity.ok(new response(id, issueKey, issue.getSelf()));
        } else {
            logger.warn("One or more required fields are missing!");
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{issueKey}")
    public ResponseEntity<Void> deleteIssue(@PathVariable String issueKey) {
        logger.info("Deleting issue with key: {}", issueKey);
        if (issues.containsKey(issueKey)) {
            issues.remove(issueKey);
            logger.info("Issue with key {} deleted", issueKey);
            return ResponseEntity.noContent().build();
        } else {
            logger.warn("Issue with key {} not found", issueKey);
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{issueKey}")
    public ResponseEntity<Task> updateIssue(@PathVariable String issueKey, @RequestBody Map<String, Object> requestBody) {
        String projectKey = (String) ((Map<String, Object>) ((Map<String, Object>) requestBody.get("fields")).get("project")).get("key");
        String summary = (String) ((Map<String, Object>) requestBody.get("fields")).get("summary");
        String description = (String) ((Map<String, Object>) requestBody.get("fields")).get("description");
        String issueType = (String) ((Map<String, Object>) ((Map<String, Object>) requestBody.get("fields")).get("issuetype")).get("name");

        logger.info("Updating issue with key: {}, updated issue: {}", issueKey);
        if (issues.containsKey(issueKey)) {
            Task issue = issues.get(issueKey);
            issue.setKey(projectKey);
            issue.setSummary(summary);
            issue.setDescription(description);
            issue.setIssueType(issueType);
            logger.info("Issue updated: {}", issue);
            return ResponseEntity.ok(issue);
        } else {
            logger.warn("Issue with key {} not found", issueKey);
            return ResponseEntity.notFound().build();
        }
    }

    private class response {
        private String id;
        private String key;
        private String self;

        response(String id, String key, String self) {
            this.id = id;
            this.key = key;
            this.self = self;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getSelf() {
            return self;
        }

        public void setSelf(String self) {
            this.self = self;
        }
    }
}



