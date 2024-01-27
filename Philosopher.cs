namespace TestTask
{
    public class Philosopher
    {
        private int id;

        private string name;

        private Fork leftFork;

        private Fork rightFork;

        public string Name
        {
            get { return name; }
        }

        public int Id
        {
            get { return id; }
        }

        public Philosopher(int id, string name, Fork leftFork, Fork rightFork)
        {
            this.id = id;
            this.name = name;
            this.leftFork = leftFork;
            this.rightFork = rightFork;
        }

        public override string ToString()
        {
            return String.Format("Philosopher {0} with id = {1}", name, id);
        }

        public void Run()
        {

            Console.WriteLine(this + " thinking about " + Database.GetRandomThoughtTopic());
            lock (leftFork)
            {
                Console.WriteLine(this + " picked left " + leftFork);
                lock (rightFork)
                {
                    Console.WriteLine(this + " picked right " + leftFork);
                    Thread.Sleep(200);
                    Console.WriteLine(this + " is eating");
                    Thread.Sleep(200);
                }
                Console.WriteLine(this + " is not hungry anymore! Put forks down.");
                Thread.Sleep(200);
            }
        }
    }
}