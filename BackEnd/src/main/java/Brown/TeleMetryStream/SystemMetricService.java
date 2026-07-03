package Brown.TeleMetryStream;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Service;

@Service
public class SystemMetricService {

    private final SystemMetricRepository systemMetricRepository;
    private final SystemMetricWebSocketHandler webSocketHandler;
    private final ObjectMapper objectMapper;

    public SystemMetricService(SystemMetricRepository systemMetricRepository, SystemMetricWebSocketHandler webSocketHandler) {
        this.systemMetricRepository = systemMetricRepository;
        this.webSocketHandler = webSocketHandler;
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule()); // Needed for Instant serialization
    }

    public SystemMetric saveMetric(SystemMetric metric) {
        SystemMetric savedMetric = systemMetricRepository.save(metric);
        
        try {
            String payload = objectMapper.writeValueAsString(savedMetric);
            webSocketHandler.broadcastMessage(payload);
        } catch (JsonProcessingException e) {
            System.err.println("Error serializing metric for broadcast: " + e.getMessage());
            // Optionally, we could just send a basic string instead of throwing
            webSocketHandler.broadcastMessage("New metric saved with ID: " + savedMetric.getId());
        }
        
        return savedMetric;
    }
}
