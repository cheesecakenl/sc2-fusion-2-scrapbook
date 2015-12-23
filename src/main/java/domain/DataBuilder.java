package domain;

import java.util.ArrayList;
import java.util.List;

public class DataBuilder {

    List<BuildOrderRecord> records = new ArrayList<BuildOrderRecord>();

    public DataBuilder(String input) {
        String[] lines = input.split("\n");

        for (String line : lines) {
            if (!isBuilderOrder(line)) {
                continue;
            }

            String shortened = shortenDescriptions(line);
            BuildOrderRecord record = new BuildOrderRecord(shortened);

            records.add(record);
        }
    }

    private String shortenDescriptions(String input) {
        input = input.replaceAll("Build ", "");
        input = input.replaceAll("Morph ", "");
        input = input.replaceAll("From Barracks With Reactor", "from reactor");
        input = input.replaceAll("From Barracks With Tech Lab", "from tech lab");
        input = input.replaceAll("From Naked Barracks", "from barracks");
        input = input.replaceAll("Move Chrono Boost From Nexus To", "Chrono boost");

        return input;
    }

    public List<BuildOrderRecord> getRecords() {
        return records;
    }

    private boolean isBuilderOrder(String line) {
        if (line == null || line.isEmpty() || !line.contains("-")) {
            return false;
        }

        if (line.contains("Waypoint")
                || line.contains("Income:")
                || line.contains("Buildings:")
                || line.contains("Units:")
                || line.contains("Research:")) {

            return false;
        }

        if (line.charAt(31) == '-' || line.charAt(36) == '-') {
            return true;
        }

        return false;
    }
}
