using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.CompilerServices;
using System.Text;
using System.Threading.Tasks;

namespace AbstractFactory
{
    internal class Program
    {
        public static void Main()
        {
            // Abstract factory #1
            Client client1 = new Client("high");
            client1.Run();

            // Abstract factory #2
            Client client2 = new Client("low");
            client2.Run();

            // Wait for user input
            Console.ReadKey();
        }
    }

    /// <summary>
    /// The 'AbstractFactory' abstract class
    /// </summary>
    abstract class AbstractFactory
    {
        public abstract Computer CreateProductA();
        public abstract TV CreateProductB();
    }



    /// <summary>
    /// The 'ConcreteFactory1' class
    /// </summary>
    class Country1 : AbstractFactory
    {
        public override Computer CreateProductA()
        {
            return new ComputerVip();
        }
        public override TV CreateProductB()
        {
            return new TVVip();
        }
    }

    /// <summary>
    /// The 'ConcreteFactory2' class
    /// </summary>
    class Country2 : AbstractFactory
    {
        public override Computer CreateProductA()
        {
            return new ComputerBase();
        }
        public override TV CreateProductB()
        {
            return new TVBase();
        }
    }

    /// <summary>
    /// The 'AbstractProductA' abstract class
    /// </summary>
    abstract class Computer
    {
        protected static double price;
        protected static string Motherboard, Keyboard, Case, Display, CoolingSystem, GPU, SSD, CPU, Accumulator;

        public abstract void Show_Info();
        public abstract void СompleteSet();
    }

    /// <summary>
    /// The 'AbstractProductB' abstract class
    /// </summary>
    abstract class TV
    {
        protected static double price;
        protected static string Displey, Diagonal, Expansion, PowerConsumptionClass, OperatingSystem, ImageProcessor;
        public abstract void Show_Info();
        public abstract void СompleteSet();
    }


    /// <summary>
    /// The 'ProductA1' class
    /// </summary>
    class ComputerVip : Computer
    {
        public override void Show_Info()
        {
            Console.WriteLine("Price = {0}, Motherboard = {1}, Keyboard = {2}, Case = {3}, Display = {4}, CoolingSystem = {5}, GPU = {6}, SSD = {7}, CPU = {8}, Accumulator = {9}", price, Motherboard, Keyboard, Case, Display, CoolingSystem, GPU, SSD, CPU, Accumulator);
        }
        public override void СompleteSet()
        {
            price = 1999;
            Motherboard = "M1 Max";
            Keyboard = "Monterey";
            Case = "Aluminum";
            Display = "Liquid Retina XDR";
            CoolingSystem = "Advanced thermal systems move 50 percent more air, even at lower fan speeds";
            GPU = "16‑core";
            SSD = "2TB";
            CPU = "10-core";
            Accumulator = "Up to 21hrs video playback, Up to 14hrs wireless web browsing";
        }
    }

    /// <summary>
    /// The 'ProductB1' class
    /// </summary>
    class TVVip : TV
    {
        public override void Show_Info()
        {
            Console.WriteLine("Price = {0}, Displey = {1}, Diagonal = {2}, Expansion = {3}, PowerConsumptionClass = {4}, OperatingSystem = {5}, ImageProcessor = {6}", price, Displey, Diagonal, Expansion, PowerConsumptionClass, OperatingSystem, ImageProcessor);
        }
        public override void СompleteSet()
        {
            price = 2166;
            Displey = "QLED 8K";
            Diagonal = "98";
            Expansion = "7680x4320 8K";
            PowerConsumptionClass = "D";
            OperatingSystem = "Tizen";
            ImageProcessor = "Quantum Processor 8K";
        }
    }

    /// <summary>
    /// The 'ProductA2' class
    /// </summary>
    class ComputerBase : Computer
    {
        public override void Show_Info()
        {
            Console.WriteLine("Price = {0}, Motherboard = {1}, Keyboard = {2}, Case = {3}, Display = {4}, CoolingSystem = {5}, GPU = {6}, SSD = {7}, CPU = {8}, Accumulator = {9}", price, Motherboard, Keyboard, Case, Display, CoolingSystem, GPU, SSD, CPU, Accumulator);
        }
        public override void СompleteSet()
        {
            price = 999;
            Motherboard = "M1";
            Keyboard = "Monterey";
            Case = "Aluminum";
            Display = "Liquid Retina";
            CoolingSystem = "Advanced thermal systems move 50 percent more air, even at lower fan speeds";
            GPU = "10‑core";
            SSD = "1TB";
            CPU = "8-core";
            Accumulator = "Up to 18hrs battery life";
        }
    }

    /// <summary>
    /// The 'ProductB2' class
    /// </summary>
    class TVBase : TV
    {
        public override void Show_Info()
        {
            Console.WriteLine("Price = {0}, Displey = {1}, Diagonal = {2}, Expansion = {3}, PowerConsumptionClass = {4}, OperatingSystem = {5}, ImageProcessor = {6}", price, Displey, Diagonal, Expansion, PowerConsumptionClass, OperatingSystem, ImageProcessor);
        }
        public override void СompleteSet()
        {
            price = 49689;
            Displey = "QLED";
            Diagonal = "55";
            Expansion = "3840x2160 UHD 4K";
            PowerConsumptionClass = "B";
            OperatingSystem = "Gear 360";
            ImageProcessor = "Q Engine";
        }
    }

    /// <summary>
    /// The 'Client' class. Interaction environment for the products.
    /// </summary>
    class Client
    {
        private Computer _abstractProductA;
        private TV _abstractProductB;
        AbstractFactory factory1 = new Country1();
        AbstractFactory factory2 = new Country2();

        // Constructor
        public Client(string price)
        {

            if(price == "low")
            {
                _abstractProductB = factory1.CreateProductB();
                _abstractProductB.СompleteSet();
                _abstractProductA = factory1.CreateProductA();
                _abstractProductA.СompleteSet();
            }
            else if(price == "high")
            {
                _abstractProductB = factory2.CreateProductB();
                _abstractProductB.СompleteSet();
                _abstractProductA = factory2.CreateProductA();
                _abstractProductA.СompleteSet();
            }
            else
                Console.WriteLine("Error");
        }

        public void Run()
        {
            _abstractProductA.Show_Info();
            _abstractProductB.Show_Info();
        }
    }
}


