namespace TestTask
{
    public class Program
    {

        private ConsoleReader reader = new ConsoleReader();

        public void Run()
        {
            int amount = reader.GetNumber(
                "Enter number of Philosophers: ",
             "It should be number with bounds = [5, 100]", 4, 100);

            Philosopher[] philosophers = PhilosophersFactory.Create(amount);

            for (int i = 0; i < philosophers.Length; i++)
            {
                Thread thread = new Thread(philosophers[i].Run);
                thread.Start();
            }
        }

        // Main method. 
        static void Main(string[] args)
        {
            new Program().Run();
        }
    }
}