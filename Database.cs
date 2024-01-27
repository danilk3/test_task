namespace TestTask
{
    public class Database
    {
        public static String[] possibleNames = { "Alex", "Danil", "Nikita", "Victor", "Anton",
         "Denis", "Artem", "Kostya", "Sergey" };


        public static String[] thoughtTopics = {"life", "food", "love", "university",
        "programming", "sky", "space", "movies", "computer games"};

        public static String GetRandomName()
        {
            return possibleNames[new Random().Next(possibleNames.Length)];
        }

        public static String GetRandomThoughtTopic()
        {
            return thoughtTopics[new Random().Next(thoughtTopics.Length)];
        }
    }
}