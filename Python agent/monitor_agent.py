import psutil
import json
import time

psutil.cpu_percent()
psutil.virtual_memory().percent
def monitor_system():
    while True:
        cpu_usage = psutil.cpu_percent(interval=1)
        memory_usage = psutil.virtual_memory().percent
        system_stats = {
            "cpu_usage": cpu_usage,
            "memory_usage": memory_usage
        }
        json_obj = json.dumps(system_stats)
        print(json_obj)
        time.sleep(2)

if __name__ == "__main__":
    try:
        monitor_system()
    except KeyboardInterrupt:
        print("Monitoring stopped by user.")
    except AttributeError as e:
        print(f"An incorrect attribute was accessed: {e}")
