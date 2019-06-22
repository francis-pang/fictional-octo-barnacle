package crackingthecodinginterview.moderate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XmlEncoding {
  private Map<String, Integer> reverseTagList;

  public String encodeXml(Element xmlElement, Map<Integer, String> tagList) {
    reverseTagList = new HashMap<>();
    tagList.forEach((key, tag) -> {
      reverseTagList.put(tag, key);
    });
    // Validation check
    return encodeElement(xmlElement);
  }

  private String encodeElement(Element element) {
    final char END = '0';
    final char EMPTY_SPACE = ' ';
    StringBuffer encodedElementStringBuffer = new StringBuffer();
    // Parse tag
    encodedElementStringBuffer.append(reverseTagList.get(element.tag));
    encodedElementStringBuffer.append(EMPTY_SPACE);

    // Parse attributes
    for (Attribute attribute : element.attributes) {
      encodedElementStringBuffer.append(attribute);
      encodedElementStringBuffer.append(EMPTY_SPACE);
    }

    // Encode END between Attributes and Children
    encodedElementStringBuffer.append(END);
    encodedElementStringBuffer.append(EMPTY_SPACE);

    // Parse children
    for (Element childElement : element.children) {
      encodedElementStringBuffer.append(encodeElement(childElement));
      encodedElementStringBuffer.append(EMPTY_SPACE);
    }

    // Encode END at the end
    encodedElementStringBuffer.append(END);
    return encodedElementStringBuffer.toString();
  }

  private StringBuffer encodeAttribute(Attribute attribute) {
    StringBuffer encodedAttributeStringBuffer = new StringBuffer();
    encodedAttributeStringBuffer.append(reverseTagList.get(attribute.tag));
    encodedAttributeStringBuffer.append(attribute.value);
    return encodedAttributeStringBuffer;
  }

  class Element {
    String tag;
    List<Attribute> attributes;
    List<Element> children;
  }

  class Attribute {
    String tag;
    String value;
  }
}
