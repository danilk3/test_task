namespace TestTask
{
    public class ConsoleReader
    {
        public int GetNumber(string message, string errorMessage, int lowerBound, int upperBound)
        {
            int result;

            Console.WriteLine(message);

            string input = Console.ReadLine();

            while (!int.TryParse(input, out result) || result <= lowerBound || result > upperBound)
            {
                Console.WriteLine(errorMessage);
                input = Console.ReadLine();
            }

            return result;
        }
    }
}