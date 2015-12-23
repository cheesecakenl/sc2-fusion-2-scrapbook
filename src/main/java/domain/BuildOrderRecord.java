package domain;

public class BuildOrderRecord {

    private String time;
    private String minerals;
    private String gas;
    private String larvae = "";
    private String supply;
    private String description;

    public BuildOrderRecord(String line) {
        try {
            time = line.substring(0, 8);
            minerals = line.substring(9, 15);
            gas = line.substring(15, 21);

            // Zerg has extra field for larvae count
            if (line.charAt(24) == 'L' || line.charAt(25) == 'L') {
                larvae = line.substring(21, 26);
                supply = line.substring(26, 35);
                description = line.substring(38, line.length());
            } else {
                supply = line.substring(21, 30);
                description = line.substring(33, line.length());
            }
        } catch (IndexOutOfBoundsException ex) {
        }

        time = time.replaceAll(" ", "");
        int dot = time.indexOf('.');
        time = time.substring(0, dot);

        minerals = minerals.replaceAll(" ", "");
        gas = gas.replaceAll(" ", "");
        larvae = larvae.replaceAll(" ", "");

        supply = supply.replaceAll(" ", "");
        supply = supply.replaceAll("S", "");
    }

    public String getTime() {
        return time;
    }

    public String getMinerals() {
        return minerals;
    }

    public String getGas() {
        return gas;
    }

    public String getLarvae() {
        return larvae;
    }

    public String getSupply() {
        return supply;
    }

    public String getDescription() {
        return description;
    }
}