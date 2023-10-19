using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Mediator
{
    // The Mediator interface declares a method used by components to notify the
    // mediator about various events. The Mediator may react to these events and
    // pass the execution to other components.
    public interface IMediator
    {
        void Notify(object sender, string ev);
    }

    // Concrete Mediators implement cooperative behavior by coordinating several
    // components.
    class ConcreteMediator : IMediator
    {
        private Component1 _component1;

        private Component2 _component2;

        private Component3 _component3;
        public ConcreteMediator(Component1 component1, Component2 component2, Component3 component3)
        {
            this._component1 = component1;
            this._component1.SetMediator(this);
            this._component2 = component2;
            this._component2.SetMediator(this);
            this._component3 = component3;
            this._component3.SetMediator(this);
        }

        public void Notify(object sender, string ev)
        {
            if (ev == "A")
            {
                Console.WriteLine("Mediator reacts on A and triggers folowing operations:");
                this._component2.DoA();
            }
            if (ev == "B")
            {
                Console.WriteLine("Mediator reacts on B and triggers following operations:");
                this._component2.DoB();
            }
            if(ev == "C")
            {

                Console.WriteLine("Mediator reacts on C and triggers following operations:");
                this._component2.DoC();
            }
            if (ev == "A1")
            {
                this._component3.DoA();
            }
            if (ev == "B1")
            {
                this._component3.DoB();
            }
            if (ev == "C1")
            {
                this._component3.DoC();
            }
            if (ev == "A2")
            {
                this._component3.DoA();
            }
            if (ev == "B2")
            {
                this._component3.DoB();
            }
        }
    }

    // The Base Component provides the basic functionality of storing a
    // mediator's instance inside component objects.
    class BaseComponent
    {
        protected IMediator _mediator;

        public BaseComponent(IMediator mediator = null)
        {
            this._mediator = mediator;
        }

        public void SetMediator(IMediator mediator)
        {
            this._mediator = mediator;
        }
    }

    // Concrete Components implement various functionality. They don't depend on
    // other components. They also don't depend on any concrete mediator
    // classes.
    class Component1 : BaseComponent
    {
        public void DoA()
        {
            Console.WriteLine("Client is in poor physical shape");

            this._mediator.Notify(this, "A");
        }

        public void DoB()
        {
            Console.WriteLine("Client is in good physical shape");

            this._mediator.Notify(this, "B");
        }
        public void DoС()
        {
            Console.WriteLine("Client is in excellent physical shape");

            this._mediator.Notify(this, "C");
        }
    }

    class Component2 : BaseComponent
    {
        public void DoA()
        {
            Console.WriteLine("The client is assigned a set of sports classes of easy difficulty");

            this._mediator.Notify(this, "A1");
        }

        public void DoB()
        {
            Console.WriteLine("The client is assigned a set of sports classes of normal difficulty");

            this._mediator.Notify(this, "B1");
        }
        public void DoC()
        {
            Console.WriteLine("The client is assigned a complex of sports classes of increased complexity");

            this._mediator.Notify(this, "C1");
        }
    }
    class Component3 : BaseComponent
    {
        public void DoA()
        {
            Console.WriteLine("head rotation, hand rotations, torso forward and backward");
        }

        public void DoB()
        {
            Console.WriteLine("push-ups 30 times, squats 60 times, Abs 50 times");

            this._mediator.Notify(this, "A2");
        }
        public void DoC()
        {
            Console.WriteLine("plank 5 minutes, side plank 3 minutes");

            this._mediator.Notify(this, "B2");
        }
    }

    class Program
    {
        static void Main(string[] args)
        {
            // The client code.
            Component1 component1 = new Component1();
            Component2 component2 = new Component2();
            Component3 component3 = new Component3();   
            new ConcreteMediator(component1, component2, component3);

            Console.WriteLine("Client triggets operation A");
            component1.DoA();

            Console.WriteLine();

            Console.WriteLine("Client triggers operation B");
            component1.DoB();
            Console.ReadKey();
        }
    }
}