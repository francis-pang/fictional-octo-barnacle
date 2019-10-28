package leetcode.google;

public class LicenseKeyFormatting {
  public String licenseKeyFormatting(String string, int k) {
    StringBuilder stringBuilder = new StringBuilder();
    int stringLength = 0;
    for (int i = string.length() - 1; i >= 0; i--) {
      if (string.charAt(i) != '-') {
        if (stringLength > 0 && stringLength % k == 0) {
          stringBuilder.append('-');
        }
        stringBuilder.append(string.charAt(i));
        stringLength++;
      }
    }
    return stringBuilder.reverse().toString().toUpperCase();
  }
}