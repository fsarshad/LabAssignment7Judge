import java.util.Arrays;

public class Judge {

    public static int findJudge(int N, int [][] trust) {
        Boolean[] people = new Boolean[N+1];
        // took boolean array of elements and initialized everything to false
        Arrays.fill(people, Boolean.FALSE);
        // looked for the first element. 1 trust component and 2 person is trusting
        for (int i = 0; i < trust.length; i++) {
            people[trust[i][0]] = Boolean.TRUE;
        }
        int n_missing = 0, sum = 0;
        // looking for how many elements are false.
        for (int i = 1; i < people.length; i++) {
            if (people[i] == Boolean.TRUE)
                sum += i;
            else
                n_missing += 1;
        }
        //  who is trusting the potential judge. checking if number of people trusting the potential judge is 1
        if (n_missing != 1) return -1;
        // potential judge will be the summation of all the elements 1 to n minus summation of all the other elements
        // but the potential judge
        int potential_judge = N*(N+1)/2 - sum;
        Arrays.fill(people, Boolean.FALSE);
        // looking through the trust array. assigning true to all the people who are trusting the potential judge,
        for (int i = 0; i < trust.length; i++) {
            if (trust[i][1] == potential_judge) {
                people[trust[i][0]] = Boolean.TRUE;
            }
        }
        // check if everyone else but the judge trust the judge.
        for (int i = 1; i < people.length; i++) {
            // potential judge can not trust himself. if the guy is potential judge then trust to himself should be false
            // if the guy is not potential judge his or her trust index should be true.
            if (!(i == potential_judge && people[i] == Boolean.FALSE) &&
                    !(i != potential_judge && people[i] == Boolean.TRUE)) {
                return -1;
            }
        }
        return potential_judge;
    }

    public static void main(String[] args) {
        System.out.println("2, {{1,2}} -> " + findJudge(2, new int [][] {{1,2}}));
        System.out.println("3, {{1,3}, {2,3}} -> " + findJudge(3, new int [][] {{1,3}, {2,3}}));
        System.out.println("3, {{1,3}, {2,3}, {3,1}} -> " + findJudge(3, new int [][] {{1,3}, {2,3}, {3,1}}));
        System.out.println("3, {{1,2}, {2,3}} -> " + findJudge(3, new int [][] {{1,2}, {2,3}}));
        System.out.println("4, {{1,3}, {1,4}, {2,3}, {2,4}, {4,3}} -> " +
                findJudge(4, new int [][] {{1,3}, {1,4}, {2,3}, {2,4}, {4,3}}));

        System.out.println("6, {{1,3}, {1,4}, {2,3}, {2,4}, {3,4}, {5,4}, {6,4}} -> " +
                findJudge(6, new int [][] {{1,3}, {1,4}, {2,3}, {2,4}, {3,4}, {5,4}, {6,4}}));
    }
}
