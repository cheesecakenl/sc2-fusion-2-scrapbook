package domain;

import org.junit.Before;
import org.junit.Test;
import util.InputUtil;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class DataBuilderTest {

    private String terranInput;
    private String protossInput;
    private String zergInput;
    private String emptyInput;
    private String invalidInput;

    @Before
    public void setUp() {
        terranInput = InputUtil.readFile("input/input-terran.txt");
        protossInput = InputUtil.readFile("input/input-protoss.txt");
        zergInput = InputUtil.readFile("input/input-zerg.txt");
        emptyInput = InputUtil.readFile("input/input-empty.txt");
        invalidInput = InputUtil.readFile("input/input-invalid.txt");
    }

    @Test
    public void testEmpty() {
        DataBuilder builder = new DataBuilder(emptyInput);
        List<BuildOrderRecord> records = builder.getRecords();

        assertEquals(0, records.size());
    }

    @Test
    public void testInvalid() {
        DataBuilder builder = new DataBuilder(invalidInput);
        List<BuildOrderRecord> records = builder.getRecords();

        assertEquals(0, records.size());
    }

    @Test
    public void testTerranRecordSize() {
        DataBuilder builder = new DataBuilder(terranInput);
        List<BuildOrderRecord> records = builder.getRecords();

        assertEquals(35, records.size());
    }

    @Test
    public void testTerranRecord() {
        DataBuilder builder = new DataBuilder(terranInput);
        List<BuildOrderRecord> records = builder.getRecords();

        BuildOrderRecord record = records.get(0);

        assertEquals("0:02", record.getTime());
        assertEquals("50M", record.getMinerals());
        assertEquals("0G", record.getGas());
        assertEquals("", record.getLarvae());
        assertEquals("12/15", record.getSupply());
        assertEquals("SCV", record.getDescription());
    }

    @Test
    public void testProtossRecordSize() {
        DataBuilder builder = new DataBuilder(protossInput);
        List<BuildOrderRecord> records = builder.getRecords();

        assertEquals(156, records.size());
    }

    @Test
    public void testProtossRecord() {
        DataBuilder builder = new DataBuilder(protossInput);
        List<BuildOrderRecord> records = builder.getRecords();

        BuildOrderRecord record = records.get(1);

        assertEquals("0:10", record.getTime());
        assertEquals("75M", record.getMinerals());
        assertEquals("0G", record.getGas());
        assertEquals("", record.getLarvae());
        assertEquals("13/15", record.getSupply());
        assertEquals("Assimilator", record.getDescription());
    }

    @Test
    public void testZergRecordSize() {
        DataBuilder builder = new DataBuilder(zergInput);
        List<BuildOrderRecord> records = builder.getRecords();

        assertEquals(37, records.size());
    }

    @Test
    public void testZergRecord() {
        DataBuilder builder = new DataBuilder(zergInput);
        List<BuildOrderRecord> records = builder.getRecords();

        BuildOrderRecord record = records.get(2);

        assertEquals("0:16", record.getTime());
        assertEquals("50M", record.getMinerals());
        assertEquals("0G", record.getGas());
        assertEquals("2L", record.getLarvae());
        assertEquals("13/14", record.getSupply());
        assertEquals("Drone", record.getDescription());
    }
}