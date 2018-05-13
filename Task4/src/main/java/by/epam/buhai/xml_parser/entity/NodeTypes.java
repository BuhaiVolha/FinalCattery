package by.epam.buhai.xml_parser.entity;

public enum NodeTypes {
    PROLOG("Prolog"),
    TAG_WITH_ATTRIBUTE("Tag with attribute"),
    OPENING_TAG("Opening tag"),
    CLOSING_TAG("Closing tag"),
    EMPTY_TAG("Empty tag"),
    TEXT_NODE("Text node");

    private String description;

    NodeTypes(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
