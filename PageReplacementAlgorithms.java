import java.util.*;

public class PageReplacementAlgorithms {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of page frames: ");
        int frames = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter String refrences (seprated by space): ");
        String num = scanner.nextLine();
        String[] pageReferenceString = num.split("\\s");
        int[] pageReference = new int[pageReferenceString.length];
        int n = pageReference.length;
        for(int i=0; i<n; i++) {
        	pageReference[i] = Integer.parseInt(pageReferenceString[i]);
        }
        System.out.println("\nFIFO Algorithm:");
        System.out.println("Hits: " + (n-fifo(pageReference, n, frames)));
        System.out.println("Faults: " + fifo(pageReference, n, frames));
        System.out.println("Hit rate: " + (double)(n-fifo(pageReference, n, frames))/n*100+"%");
        System.out.println("Fault rate: " + (double)fifo(pageReference, n, frames)/n*100+"%");
        System.out.println("\nLIFO Algorithm:");
        System.out.println("Hits: " + (n-lifo(pageReference, n, frames)));
        System.out.println("Faults: " + lifo(pageReference, n, frames));
        System.out.println("Hit rate: " + (double)(n-lifo(pageReference, n, frames))/n*100+"%");
        System.out.println("Fault rate: " + (double)lifo(pageReference, n, frames)/n*100+"%");
        System.out.println("\nLRU Algorithm:");
        System.out.println("Hits: " + (n-lru(pageReference, n, frames)));
        System.out.println("Faults: " + lru(pageReference, n, frames));
        System.out.println("Hit rate: " + (double)(n-lru(pageReference, n, frames))/n*100+"%");
        System.out.println("Fault rate: " + (double)lru(pageReference, n, frames)/n*100+"%");
        System.out.println("\nOptimal Algorithm:");
        System.out.println("Hits: " + (n-optimal(pageReference, n, frames)));
        System.out.println("Faults: " + optimal(pageReference, n, frames));
        System.out.println("Hit rate: " + (double)(n-optimal(pageReference, n, frames))/n*100+"%");
        System.out.println("Fault rate: " + (double)optimal(pageReference, n, frames)/n*100+"%");
        System.out.println("\nRandom Page Replacement Algorithm:");
        System.out.println("Hits: " + (n-random(pageReference, n, frames)));
        System.out.println("Faults: " + random(pageReference, n, frames));
        System.out.println("Hit rate: " + (double)(n-random(pageReference, n, frames))/n*100+"%");
        System.out.println("Fault rate: " + (double)random(pageReference, n, frames)/n*100+"%");
    }
    
    public static int fifo(int[] pageReference, int n, int frames) {
        int pageFaults = 0;
        Queue<Integer> frame = new LinkedList<>();
        
        for (int i = 0; i < n; i++) {
            if (frame.contains(pageReference[i])) {
                continue;
            }
            if (frame.size() == frames) {
                frame.poll();
            }
            frame.add(pageReference[i]);
            pageFaults++;
        }
        return pageFaults;
    }
    
    public static int lru(int[] pageReference, int n, int frames) {
        int pageFaults = 0;
        List<Integer> frame = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            if (frame.contains(pageReference[i])) {
                frame.remove(Integer.valueOf(pageReference[i]));
            } else {
                if (frame.size() == frames) {
                    frame.remove(0);
                }
                pageFaults++;
            }
            frame.add(pageReference[i]);
        }
        return pageFaults;
    }
    
    public static int optimal(int[] pageReference, int n, int frames) {
        int pageFaults = 0;
        List<Integer> frame = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            if (frame.contains(pageReference[i])) {
                continue;
            }
            if (frame.size() == frames) {
                int index = -1, farthest = i + 1;
                for (int j = 0; j < frame.size(); j++) {
                    int temp = i;
                    while (temp < n) {
                        if (pageReference[temp] == frame.get(j)) {
                            if (temp > farthest) {
                                farthest = temp;
                                index = j;
                            }
                            break;
                        }
                        temp++;
                    }
                    if (temp == n) {
                        index = j;
                        break;
                    }
                }
                frame.remove(index);
            }
            frame.add(pageReference[i]);
            pageFaults++;
        }
        return pageFaults;
    }
    public static int lifo(int[] pageReference, int n, int frames) {
        int pageFaults = 0;
        Stack<Integer> frame = new Stack<>();
        
        for (int i = 0; i < n; i++) {
            if (frame.contains(pageReference[i])) {
                frame.removeElement(pageReference[i]);
            } else {
                if (frame.size() == frames) {
                    frame.pop();
                }
                pageFaults++;
            }
            frame.push(pageReference[i]);
        }
        return pageFaults;
    }
    public static int random(int[] pageReference, int n, int frames) {
        int pageFaults = 0;
        List<Integer> frame = new ArrayList<>();
        Random rand = new Random();
        
        for (int i = 0; i < n; i++) {
            if (frame.contains(pageReference[i])) {
                continue;
            }
            if (frame.size() == frames) {
                int randomIndex = rand.nextInt(frames);
                frame.remove(randomIndex);
            }
            frame.add(pageReference[i]);
            pageFaults++;
        }
        return pageFaults;
    }
}

