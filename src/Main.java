import Controller.WordPredictor;
import Model.KeyboardListener;
import View.View;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import static javafx.application.Application.launch;

public class Main {



    public static void main(String[] args) {
        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException ex) {

        }

        KeyboardListener kb = new KeyboardListener();
        GlobalScreen.addNativeKeyListener(kb);
        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.WARNING);
        logger.setUseParentHandlers(false);
        Application.launch(View.class, args);
        System.err.println(kb.getOutStream());
//        TreeSet<Controller.WordNode> set = new TreeSet<>();
//        Controller.WordNode wn = new Controller.WordNode("hello", 1000);
//        set.add(wn);
//        set.add(new Controller.WordNode("a", 999));
//        set.add(new Controller.WordNode("jkl", 1001));
//        set.add(new Controller.WordNode("av", 1000));
//        System.out.println(set);
//        set.remove(wn);
//        System.out.println(set);
//        System.out.println(set.first());
        WordPredictor wordPredictor = new WordPredictor();



        String s = "Thank you, very much. Thank you. Thank you, very much.\n" +
                "\n" +
                "My friends, fellow Democrats, fellow Americans:\n" +
                "\n" +
                "I’m going to be candid with you. I had hoped to be back here this week under different circumstances, running for re-election. But you know the old saying: you win some, you lose some. And then there’s that little-known third category.\n" +
                "\n" +
                "\n" +
                "\n" +
                "But I didn’t come here tonight to talk about the past. After all, I don’t want you to think that I lie awake at night counting and recounting sheep. I prefer to focus on the future, because I know from my own experience that America’s a land of opportunity, where every little boy and girl has a chance to grow up and win the popular vote.\n" +
                "\n" +
                "In all seriousness, I am deeply, deeply grateful for the opportunity you have given me to serve my country. I want to thank you as Democrats for the honor of being your nominee for President four years ago and for all you did for me and for our country. And I want to thank the American people for the privilege of serving as Vice President of the United States. Most of all, I want to thank my family with all my heart -- my children and grandchildren, especially my beloved partner in life, Tipper.\n" +
                "\n" +
                "I love this country deeply. Wasn’t BeBe Winans great? I believe that’s the best national anthem I’ve ever heard sung. I love this country deeply, and even though I always look to the future with optimism and hope, I do think it’s worth pausing for just a moment as we begin this year’s convention, to take note of two very important lessons from four years ago.\n" +
                "\n" +
                "The first lesson is this: Take it from me; every vote counts. In our democracy, every vote has power. And never forget that power is yours. Don’t let anyone take it away from you or talk you into throwing it away.\n" +
                "\n" +
                "And let’s make sure that this time every vote is counted. Let’s make sure that the Supreme Court does not pick the next President, and that this President is not the one who picks the next Supreme Court.\n" +
                "\n" +
                "The second lesson from 2000 is this: What happens in a presidential election matters -- a lot. The outcome profoundly affects the lives of all 293 million Americans, and people in the rest of the world, too. The choice of who is president affects your life and your family’s future.\n" +
                "\n" +
                "And never has that been more true than in 2000 and 4, because let’s face it our country faces deep challenges. These challenges we now confront are not Democratic or Republican challenges; they are American challenges that we all must overcome together as one people, as one nation.\n" +
                "\n" +
                "And it is in that spirit, that I sincerely ask those watching at home tonight who supported President Bush four years ago: did you really get what you expected from the candidate you voted for? Is our country more united today? Or more divided? Has the promise of compassionate conservatism been fulfilled? Or do those words now ring hollow?\n" +
                "\n" +
                "For that matter, are the economic policies really conservative at all? For example, did you expect the largest deficits in history, year after year? One right after another? And the loss of more than a million jobs?\n" +
                "\n" +
                "By the way, I know about the bad economy. I was the first one laid off. And while it’s true that new jobs are being created, they’re just not as good as the jobs people have lost. And incidentally, that’s been true for me too. Unfortunately, this is no joke for millions of Americans. And the real solutions require us to transcend partisanship. So that’s one reason why, even though we meet here as Democrats, we believe this is a time to reach beyond our party lines to Republicans as well.\n" +
                "\n" +
                "And I also ask tonight for the consideration and the help of those who supported a third party candidate in 2000. I urge you to ask yourselves this question: Do you still believe that there was no difference between the candidates? Are you troubled by the erosion of America’s most basic civil liberties? Are you worried that our environmental laws are being weakened and dismantled to allow vast increases in pollution that are contributing to a global climate crisis? No matter how you voted in the last election, these are profound problems that all voters must take into account this November 2nd.\n" +
                "\n" +
                "And of course, no challenge is more critical than the situation we confront in Iraq. Regardless of your opinion at the beginning of this war, isn’t it now abundantly obvious that the way this war has been managed by the administration has gotten us into very serious trouble? Wouldn’t we be better off with a new President who hasn’t burned his bridges to our allies, and who can rebuild respect for America in the world? Isn’t cooperation with other nations crucial to solving our dilemma in Iraq? Isn’t it also critical to defeating the terrorists?\n" +
                "\n" +
                "We have to be crystal clear about the threat we face from terrorism. It is deadly. It is real. It is imminent. But in order to protect our people, shouldn’t we focus on the real source of this threat: the group that attacked us and is trying to attack us again: Al Qaeda, headed by Osama bin Laden? Wouldn’t we be safer with a President who didn’t insist on confusing Al Qaeda with Iraq? Doesn’t that divert too much of our attention away from the principal danger?\n" +
                "\n" +
                "I want to say to all Americans this evening that whether it’s the threat to the global environment or the erosion of America’s leadership in the world, whether it is the challenge to our economy from new competitors or the challenge to our security from new enemies, I believe we need new leadership that is both strong and wise. And we can have new leadership, because one of our greatest strengths as a democracy is that when we’re headed in the wrong direction, we can correct our course. When policies are clearly not working, we, the people, can change them. If our leaders make mistakes, we can hold them accountable -- even if they never admit their mistakes.\n" +
                "\n" +
                "I firmly believe America needs new leadership that will make us stronger at home and respected in the world.And we’re here this week to present to the nation the man who should be and will be our new president: John Kerry.\n" +
                "\n" +
                "John and I were elected to the United States Senate on the same day 20 years ago, and I have worked closely with him for all that time. So I want to say a personal word about John Kerry the man. He is a friend who will stand by you. His word is his bond. He has a deep patriotism that goes far beyond words. He has devoted his life to making America a better place for all of us.\n" +
                "\n" +
                "He showed uncommon heroism on the battlefield in Vietnam. I watched him show that same courage on the Senate floor. For example, he had the best record of protecting the environment against polluters of any of my colleagues bar none. He never shied away from a fight, no matter how powerful the foe. He was never afraid to take on difficult and thankless issues that few others wanted to touch -- like exposing the threat of narcoterrorism and tracing the sources of terrorist financing. He was one of the very first in our Party to take on the issue of drastic deficit reduction. And he’s developed a tough and thoughtful plan to restore our economic strength and fiscal discipline.\n" +
                "\n" +
                "To put it simply, those of us who have worked with John know that he has the courage, integrity and leadership to be a truly great President of the United States of America.\n" +
                "\n" +
                "And he showed wisdom in his very first decision as the leader of our Party when he picked as his running mate an inspiring fighter for middle class families and families struggling to reach the middle class: John Edwards of North Carolina.\n" +
                "\n" +
                "John Kerry and John Edwards are fighting for us and for all Americans, so after we nominate them here in Boston and return back to our home states across this land, we have to fight for them. Talk to your friends and neighbors, go to \"JohnKerry.com,\" raise money, register voters, get 'em to the polls, volunteer your time, and above all: make your vote count.\n" +
                "\n" +
                "To those of you who felt disappointed or angry with the outcome in 2000, I want you to remember all of those feelings. But then I want you to do with them what I have done: focus them fully and completely on putting John Kerry and John Edwards in the White House in 2000 and 4, so we can have a new direction in America, a new President, a new Vice President, new policies, a new day, a brighter future -- what this country and what our people deserve.\n" +
                "\n" +
                "Fellow Democrats, when I look out and see so many friends who have meant so much to me in my own public service, my heart is full tonight. I thank you for all the love you’ve shown to Tipper and me. You will forever be in our hearts.\n" +
                "\n" +
                "And there’s someone else I’d United States like to thank, and that’s the man who asked me to join him on the ticket at our convention 12 years ago, my friend and my partner for eight years: President Bill Clinton. I will never forget that convention or that campaign the way we barnstormed our country, carrying a message of hope and change, believing with our whole hearts that America could be made new again. And so it was. And with your help, and with the leadership of John Kerry and John Edwards, so it shall be again.\n" +
                "\n" +
                "Thank you, God bless you, and may God b United States sless the United States. a new a new a new a new a new a new a new";
//        s = s.replace("\n", " ");
//        String[] strings = s.split(" ");
//
//
//        for (int i = 0; i < strings.length-1; i++) {
//            wordPredictor.putWord(strings[i], strings[i+1]);
//        }
//
//        System.out.println(wordPredictor);
//
//        System.out.println(wordPredictor.getPredictedWord("a"));
//        System.out.println(wordPredictor.getPredictedWord("united"));
//
//        Scanner scanner = new Scanner(System.in);
//        String s1 = scanner.nextLine();
//
//        String[] s11 = s1.split(" ");
//
//        for (int i = 0; i < s11.length-1; i++) {
//            System.out.println(s11[i] + ": " + wordPredictor.getPredictedWord(s11[i]));
//            wordPredictor.putWord(s11[i], s11[i+1]);
//        }
    }


}
