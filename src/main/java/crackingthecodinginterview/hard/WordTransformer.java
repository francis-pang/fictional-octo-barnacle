package crackingthecodinginterview.hard;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WordTransformer {
  private Map<String, LinkedList<String>> stringTransformationPathMap;

  public List<String> transformWords(List<String> dictionary, String source, String target) {
    stringTransformationPathMap = new HashMap<>();
    Set<String> dictionarySet = transformListToSet(dictionary);
    return transformWords(dictionarySet, source, target);
  }

  private Set<String> transformListToSet(List<String> dictionary) {
    Set<String> transformedSet = new HashSet<>();
    transformedSet.addAll(dictionary);
    return transformedSet;
  }

  private LinkedList<String> transformWords(Set<String> dictionary, String source, String target) {
    LinkedList<String> transformationPath = new LinkedList<>();
    if (source.equals(target)) {
      transformationPath.add(source);
      return transformationPath;
    }
    if (stringTransformationPathMap.containsKey(source)) {
      return stringTransformationPathMap.get(source);
    }

    char[] sourceArray = source.toCharArray();
    char[] targetArray = target.toCharArray();
    boolean foundAPath = false;
    for (int index = 0; index < sourceArray.length; index++) {
      if (sourceArray[index] == targetArray[index]) {
        continue;
      }
      char[] modifiedSourceArray = sourceArray.clone();
      modifiedSourceArray[index] = targetArray[index];
      String modifiedSource = modifiedSourceArray.toString();
      if (dictionary.contains(modifiedSource)) {
        transformationPath = transformWords(dictionary, modifiedSource, target);
        if (transformationPath != null) {
          transformationPath.addFirst(modifiedSource);
          stringTransformationPathMap.put(modifiedSource, transformationPath);
          return transformationPath;
        }
      } else {
        stringTransformationPathMap.put(modifiedSource, null);
      }
    }
    return null;
  }
}
