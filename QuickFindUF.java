public class QuickFindUF {
    private int[] id;

    // Constructor
    public QuickFindUF(int N) {
        // Initialize our array setting each object connected to itself
        id = new int[N];
        for(int i=0; i < N; i++) {
            id[i] = i;
        }
    }

    // Find operation
    public boolean connected(int p, int q) {
        return id[p] == id[q];
    }

    // Join two objects into the same component
    public void union(int p, int q) {
        int pid = id[p];
        int qid = id[q];
        for(int i=0; i<id.length; i++) {
            if(id[i] == pid) {
                id[i] = qid;
            }
        }
    }

    // Show the id string
    public void show() {
        String out = "";
        for(int i=0; i<id.length; i++) {
            out += id[i];
            if(i < id.length-1) out += ' ';
        }
        System.out.println(out);
    }
}