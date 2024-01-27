namespace TestTask
{
    public class Fork
    {
        private int id;

        public int Id
        {
            get { return id; }
        }

        public Fork(int id) {
            this.id = id;
        }

        public override string ToString() {
             return String.Format("fork with id = {0}", id);
        }
    }
}