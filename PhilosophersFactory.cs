namespace TestTask
{
    public class PhilosophersFactory
    {

        public static Philosopher[] Create(int amount)
        {
            Philosopher[] philosophers = new Philosopher[amount];
            Fork[] forks = CreateForks(amount);

            for (int i = 0; i < amount; i++)
            {
                Fork leftFork = forks[i];
                Fork rightFork = forks[(i + 1) % forks.Length];
                if (i == philosophers.Length - 1)
                {
                    philosophers[i] = new Philosopher(i, Database.GetRandomName(), rightFork, leftFork);
                }
                else
                {
                    philosophers[i] = new Philosopher(i, Database.GetRandomName(), leftFork, rightFork);
                }
            }

            return philosophers;
        }

        private static Fork[] CreateForks(int amount)
        {
            Fork[] forks = new Fork[amount];

            for (int i = 0; i < amount; i++)
            {
                forks[i] = new Fork(i);
            }

            return forks;
        }
    }
}