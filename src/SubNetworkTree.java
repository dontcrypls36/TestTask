//just for comparison efficiency
public class SubNetworkTree {

    public static void main(String[] args) {
        String[] ips = {};
        Tree.Node node = new Tree.Node((short) 0);
        Tree tree = new Tree(node);
        for (String arg : ips) {
            String[] bytes = arg.split("\\.");
            short b1 = Short.parseShort(bytes[0]);
            short b2 = Short.parseShort(bytes[1]);
            short b3 = Short.parseShort(bytes[2]);
            short b4 = Short.parseShort(bytes[3]);
            Tree.Node b1Node = addNode(tree.getRoot(), b1);
            Tree.Node b2Node = addNode(b1Node, b2);
            Tree.Node b3Node = addNode(b2Node, b3);
            addNode(b3Node, b4);
        }
        Tree.Node curr = tree.getRoot();
        String subnetwork = "*.*.*.*";
        while (curr.getChildren().size() == 1) {
            subnetwork = subnetwork.replaceFirst("\\*", "" + curr.getChildren().stream().findFirst().get().getValue());
            curr = curr.getChildren().stream().findFirst().get();
        }
        System.out.println(subnetwork);
    }

    public static Tree.Node addNode(Tree.Node root, short newValue) {
        final Tree.Node node = new Tree.Node(newValue);
        if (!root.getChildren().contains(node)) {
            root.getChildren().add(node);
            return node;
        } else {
            return root.getChildren().stream().filter(n -> n.getValue() == node.getValue()).findFirst().get();
        }

    }
}
