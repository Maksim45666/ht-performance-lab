import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.SerializationFeature;
import tools.jackson.databind.node.ObjectNode;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class task3 {

    private static void fillValues(ObjectNode obj, Map<Integer,String> map) {

        int id;
        String value;
        JsonNode node;

        if (obj.has("id") && obj.has("value")) {
            id = obj.get("id").asInt();
            value = map.get(id);
            if (value != null) {
                obj.put("value", value);
            }
        }

        for (String fieldName : obj.propertyNames()) {
            node = obj.get(fieldName);

            if (node.isObject()) {
                fillValues((ObjectNode) node, map);
            } else if (node.isArray()) {
                processArray(node, map);
            }
        }
    }

    private static void processArray(JsonNode array, Map<Integer, String> map) {
        for (JsonNode item : array) {
            if(item.isObject()) {
                fillValues((ObjectNode) item, map);
            } else if (item.isArray()) {
                processArray(item, map);
            }
        }
    }

    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Ошибка: нужно 3 аргумента");
            return;
        }

        File file;

        String[] inputFiles = {args[0], args[1]};

        for (String fileName : inputFiles) {
            file = new File(fileName);
            if (!file.exists()) {
                System.out.println("Ошибка файла " + fileName + " не найден!");
                return;
            }
        }

        ObjectMapper mapper = new ObjectMapper();

        JsonNode valueFile = mapper.readTree(new File(args[0]));
        JsonNode valuesArray = valueFile.get("values");

        Map<Integer, String> map = new HashMap<>();
        int id = 0;
        String value;
        for (JsonNode node : valuesArray) {
            id = node.get("id").asInt();
            value = node.get("value").asString();
            map.put(id, value);
        }

        ObjectNode tests = (ObjectNode) mapper.readTree(new File(args[1]));
        fillValues(tests, map);
        mapper.writerWithDefaultPrettyPrinter()
                .writeValue(new File(args[2]), tests);
        System.out.println("Отчет сохранен!");
    }
}
