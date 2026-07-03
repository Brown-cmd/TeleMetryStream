package Brown.TeleMetryStream;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.Instant;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "system_metrics")

public class SystemMetric {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cpu_usage", nullable = false)
    private String cpuUsage;

    @Column(name = "memory_usage", nullable = false)
    private String memoryUsage;

    @Column(name = "Time_stamp", updatable = false)
    private Instant timestamp;

    @Column(name = "service_name", nullable = false)
    private String serviceName;

    @Column(name = "active_threads")
    private int activeThreads;

}
