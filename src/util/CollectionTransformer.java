package util;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

/**
 * Transforms collections from one kind to another.
 * @author Mike Liu
 *
 */
public class CollectionTransformer {

    public static List<Entry<String, Double>> mapToSortedList(Map<String, Double> map) {
        return map.entrySet().stream()
                .sorted(Comparator.comparing(Entry::getKey))
                .collect(Collectors.toList());
    }
    
}
