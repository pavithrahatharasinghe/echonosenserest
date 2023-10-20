import com.example.echonosenserest.models.SubscriptionHistory;
import org.junit.jupiter.api.Test;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import com.example.echonosenserest.database.subscriptionhistoryDBUtils;

import static org.junit.jupiter.api.Assertions.*;

class subscriptionhistoryDBUtilsTest {

    @Test
    void testGetAllSubscriptionHistories() {
        try {
            List<SubscriptionHistory> histories = subscriptionhistoryDBUtils.getAllSubscriptionHistories();
            assertNotNull(histories);
            assertTrue(histories.size() > 0);
        } catch (SQLException e) {
            fail("Exception thrown while retrieving subscription histories: " + e.getMessage());
        }
    }

    @Test
    void testGetSubscriptionHistoriesByUserId() {
        try {
            int userId = 123; // Replace with an existing user ID
            List<SubscriptionHistory> histories = subscriptionhistoryDBUtils.getSubscriptionHistoriesByUserId(userId);
            assertNotNull(histories);
        } catch (SQLException e) {
            fail("Exception thrown while retrieving user-specific subscription histories: " + e.getMessage());
        }
    }
}
