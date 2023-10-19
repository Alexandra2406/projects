using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Xml.Linq;

namespace Adapter
{
    /// <summary>
    /// MainApp startup class for Structural
    /// Adapter Design Pattern.
    /// </summary>
    class MainApp
    {
        /// <summary>
        /// Entry point into console application.
        /// </summary>
        static void Main()
        {
            // Create adapter and place a request
            User target = new Adapter("Oleksandra", "Kurulchyk", "Arthurivna", 36);
            target.Greeting();

            // Wait for user
            Console.ReadKey();
        }
    }

    /// <summary>
    /// The 'Target' class
    /// </summary>
    class User
    {
        public string name;
        public string lastname;
        public string patronymic;
        public int age;
        public User(string name, string lastname, string patronymic, int age)
        {
            this.name = name;
            this.lastname = lastname;
            this.patronymic = patronymic;
            this.age = age;
        }
        public User(string name, string lastname, string patronymic)
        {
            this.name = name;
            this.lastname = lastname;
            this.patronymic = patronymic;
            this.age = -1;
        }
        public virtual void Greeting()
        {
            Console.WriteLine("Привіт {0}", name);
        }
    }

    /// <summary>
    /// The 'Adapter' class
    /// </summary>
    class Adapter : User
    {
        private AdapteeUser _adaptee = new AdapteeUser();

        public Adapter(string name, string lastname, string patronymic, int age) : base(name, lastname, patronymic, age)
        { }
        public Adapter(string name, string lastname, string patronymic) : base(name, lastname, patronymic)
        {
            this.age = -1;
        }
        public override void Greeting()
        {
            // Possibly do some other work
            //  and then call SpecificRequest
            _adaptee.SpecificRequest(this);
        }
    }

    /// <summary>
    /// The 'Adaptee' class
    /// </summary>
    class AdapteeUser
    {

        public void SpecificRequest(User user)
        {
            if (user.age == -1)
                Console.WriteLine("Добрий день {0} {1} {2}", user.name, user.lastname, user.patronymic);
            else if (user.age >= 35)
                Console.WriteLine("Добрий день {0} {1} {2}", user.name, user.lastname, user.patronymic);
            else 
                Console.WriteLine("Привiт {0}", user.name);
        }
    }
}

