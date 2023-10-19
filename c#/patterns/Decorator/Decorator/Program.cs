using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Decorator
{
    /// <summary>
    /// MainApp startup class for Structural
    /// Decorator Design Pattern.

    /// </summary>
    class MainApp
    {
        /// <summary>
        /// Entry point into console application.
        /// </summary>

        static void Main()
        {
            // Create ConcreteComponent and two Decorators
            ConcreteComponent c = new ConcreteComponent(10, 4, "zoo", 6, "puma");
            LargePredators d1 = new LargePredators();
            DomesticCats d2 = new DomesticCats();
            Personalized d3 = new Personalized();

            // Link decorators
            d1.SetComponent(c);
            d2.SetComponent(d1);
            d3.SetComponent(d2);
            d3.Name = "Cleo";
            
            d3.Print();
            d3.Behavior();
            // Wait for user
            Console.ReadKey();
        }
    }


    /// <summary>
    /// The 'Component' abstract class
    /// </summary>

    abstract class Felidae //Тварина сімейства котячі
    {
        protected double height;
        protected double weight;
        protected string habitat;
        protected double age;
        protected string breed;
        protected string name;
        public abstract void Print();
        public abstract void Behavior();
    }

    /// <summary>
    /// The 'ConcreteComponent' class
    /// </summary>

    class ConcreteComponent : Felidae
    {
        public ConcreteComponent(double height, double weight, string habitat, double age, string breed)
        {
            this.height = height;
            this.weight = weight;
            this.habitat = habitat;
            this.age = age;
            this.breed = breed;
        }
       
        public override void Print()
        {
            Console.Write("Height = {0}, Weight = {1}, Habitat = {2}, Age = {3}, Breed = {4}", height, weight, habitat, age, breed);
        }
        public override void Behavior()
        {
            Console.WriteLine();
            Console.Write("Можуть їсти, спати");
        }
    }

    /// <summary>
    /// The 'Decorator' abstract class
    /// </summary>

    abstract class Decorator : Felidae
    {
        protected Felidae cat;

        public void SetComponent(Felidae component)
        {
            this.cat = component;
        }
        public override void Print()
        {
            if (cat != null) cat.Print();
        }
        public override void Behavior()
        {
            if (cat != null)
            {
                cat.Behavior();
            }
        }
    }


    /// <summary>
    /// The 'ConcreteDecoratorA' class
    /// </summary>

    class LargePredators : Decorator
    {
        public override void Behavior()
        {
            base.Behavior();
            AddedBehavior();
        }
        void AddedBehavior()
        {
            Console.Write(", полюють на здобич");
        }
    }

    /// <summary>
    /// The 'ConcreteDecoratorB' class
    /// </summary>

    class DomesticCats : Decorator
    {
       
        public override void Behavior()
        {
            base.Behavior();
            AddedBehavior();
        }
        void AddedBehavior()
        {
            Console.Write(", граються з сонячним зайчиком");
        }
    }
    class Personalized : Decorator
    {
        public string Name { get { return this.name; } set { this.name = value; } }
        public override void Print()
        {
            base.Print();
            Console.WriteLine(", Name = {0}", Name);
        }
        public override void Behavior()
        {
            base.Behavior();
            AddedBehavior();
        }
        void AddedBehavior()
        {
            Console.WriteLine(", мають iм'я");
        }
    }
}



