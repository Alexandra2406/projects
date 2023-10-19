using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ChainOfResponsibility
{
    // The Handler interface declares a method for building the chain of
    // handlers. It also declares a method for executing a request.
    public interface IHandler
    {
        IHandler SetNext(IHandler handler);

        object Handle(object request);
    }

    // The default chaining behavior can be implemented inside a base handler
    // class.
    abstract class AbstractHandler : IHandler
    {
        private IHandler _nextHandler;

        public IHandler SetNext(IHandler handler)
        {
            this._nextHandler = handler;

            // Returning a handler from here will let us link handlers in a
            // convenient way like this:
            // monkey.SetNext(squirrel).SetNext(dog);
            return handler;
        }

        public virtual object Handle(object request)
        {
            if (this._nextHandler != null)
            {
                return this._nextHandler.Handle(request);
            }
            else
            {
                return null;
            }
        }
    }

    class Friend1 : AbstractHandler
    {
        public override object Handle(object request)
        {
            if (Convert.ToInt32(request) <= 5000)
            {
                return $"Friend1: I'll borrow you {request.ToString()}.\n";
            }
            else
            {
                return base.Handle(request);
            }
        }
    }

    class Friend2 : AbstractHandler
    {
        public override object Handle(object request)
        {
            if (Convert.ToInt32(request) <= 10000)
            {
                return $"Friend2: I'll borrow you {request.ToString()}.\n";
            }
            else
            {
                return base.Handle(request);
            }
        }
    }

    class Friend3 : AbstractHandler
    {
        public override object Handle(object request)
        {
            if (Convert.ToInt32(request) <= 50000)
            {
                return $"Friend3: I'll borrow you {request.ToString()}.\n";
            }
            else
            {
                return base.Handle(request);
            }
        }
    }

    class Client
    {
        // The client code is usually suited to work with a single handler. In
        // most cases, it is not even aware that the handler is part of a chain.
        public static void ClientCode(AbstractHandler handler)
        {
            foreach (var sum in new List<int> { 3000, 7000, 20000, 100000 })
            {
                Console.WriteLine($"Client: Hi! Can you lend me?");

                var result = handler.Handle(sum);

                if (result != null)
                {
                    Console.Write($"   {result}");
                }
                else
                {
                    Console.WriteLine($"   {sum} is too high, credit");
                }
            }
        }
    }

    class Program
    {
        static void Main(string[] args)
        {
            // The other part of the client code constructs the actual chain.
            var friend1 = new Friend1();
            var friend2 = new Friend2();
            var friend3 = new Friend3();

            friend1.SetNext(friend2).SetNext(friend3);

            // The client should be able to send a request to any handler, not
            // just the first one in the chain.
            Console.WriteLine("Chain: Friend1 > Friend2 > Friend3\n");
            Client.ClientCode(friend1);
            Console.WriteLine();
            Console.ReadKey();
        }
    }
}