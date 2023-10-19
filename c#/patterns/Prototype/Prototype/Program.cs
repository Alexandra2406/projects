using System;
using System.IO;
using System.Runtime.Serialization.Formatters.Binary;
using System.Runtime.Serialization;

namespace Prototype
{
    internal class Program
    {
        static void Main(string[] args)
        {

            // Create two instances and clone each
            string[] contacts = new string[] { "1", "2" };
            string[] contacts2 = new string[] { "3", "4" };
            ChatRoom p1 = new ChatRoom(contacts);
            ChatRoom c1 = (ChatRoom)p1.Clone();
            p1.Contacts = contacts2;
            p1.Print();
            Console.WriteLine("Cloned: ");
            c1.Print();
            ChatRoom c2 = (ChatRoom)p1.DeepClone();
            p1.Contacts = contacts;
            p1.Print();
            Console.WriteLine("Cloned: ");
            c2.Print();
            // Wait for user
            Console.ReadKey();


        }
    }
    /// <summary>
    /// The 'Prototype' abstract class
    /// </summary>
    [Serializable]
    abstract class Prototype<T>
    {        
        public abstract T Clone();
        public abstract object DeepClone();
    }

    /// <summary>
    /// A 'ConcretePrototype' class 
    /// </summary>
    [Serializable]
    class ChatRoom : Prototype<ChatRoom>
    {
        private string[] contacts;

        // Constructor
        public ChatRoom(string[] contacts)
        {
            this.contacts = contacts;
        }

        // Gets/Set contacts

        public string[] Contacts
        {
            get { return contacts; }
            set { contacts = value; }
        }
        public void Print()
        {
            foreach (string contact in contacts)
                Console.Write(contact + " ");
            Console.WriteLine();
        }

        // Returns a shallow copy
        public override ChatRoom Clone()
        {
            return this.MemberwiseClone() as ChatRoom;
        }
        //public override ChatRoom DeepClone()
        //{
        //    var newChatRoom = Clone();
        //    newChatRoom.Contacts = this.Contacts;
        //    return newChatRoom;
        //}
        public override object DeepClone()
        {
            object newChatRoom = null;
            using (MemoryStream tempStream = new MemoryStream())
            {
                BinaryFormatter binFormatter = new BinaryFormatter(null,
                    new StreamingContext(StreamingContextStates.Clone));

                binFormatter.Serialize(tempStream, this);
                tempStream.Seek(0, SeekOrigin.Begin);

                newChatRoom = binFormatter.Deserialize(tempStream);
            }
            return newChatRoom;
        }
    }
}

