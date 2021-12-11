import xml.etree.ElementTree as ET
import fileinput
import sys

ET.register_namespace("", "http://maven.apache.org/POM/4.0.0")
version = sys.argv[0]
tree = ET.parse("pom.xml")
root = tree.getroot()
print("old text=", root[4].text)
root[4].text = version
print("new text=", root[4].text)
tree.write("pom.xml")

for line in fileinput.input("src/main/java/com/pun/poc/models/CommitOne.java", inplace = True):
    if "0.0.1-SNAPSHOT" in line:
        print(line.replace("0.0.1-SNAPSHOT", version), end='')
    else:
        print(line, end='')
