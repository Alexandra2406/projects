using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Shapes;
using System.Threading;
using System.Diagnostics;
using System.IO;

namespace Prac1
{
    /// <summary>
    /// Логика взаимодействия для StudyModeWindow.xaml
    /// </summary>
    public partial class StudyModeWindow : Window
    {
        private void CloseStudyMode_Click(object sender, RoutedEventArgs e)
        {
            StreamWriter result = new StreamWriter("Result.txt");
            result.Write(string.Empty);
            MainWindow mw = new MainWindow();
            Hide();
            mw.Show();
        }
        public StudyModeWindow()
        {

            InitializeComponent();
            stopWatch = new Stopwatch();
            stopWatch.Start();
            t0 = 0;
        }
        Stopwatch stopWatch;
        TimeSpan ts;
        double t0, t1;
        int count = 0, h = 1, m = 1;

        private void OnKeyDown(object sender, KeyEventArgs e)
        {
            StreamWriter result = File.AppendText("Result.txt");
            ts = stopWatch.Elapsed;
            if (count > 0 && count < 10)
            {
                t1 = ts.TotalSeconds;
                result.Write((Math.Round(t1 - t0, 2)) + " ");
                t0 = t1;
                Symbol.Content = m++;
            }
            else
            {
                t0 = ts.TotalSeconds;
                Symbol.Content = m++;
            }
            count++;
            if (count == 10)
            {
                Symbol.Content = m++;
                result.Close();
                count = 0;
                InputField.IsEnabled = false;   
                Statistic();
                InputField.Text = string.Empty;
            }
            result.Close();
        }

        private void Statistic()
        {
            if (int.Parse(Count.Text) > int.Parse(Convert.ToString(SymbolCount.Content)))
            {
                
                StreamReader resul = new StreamReader("Result.txt");
                string[] Line = resul.ReadLine().Split(' ', '\n', (char)StringSplitOptions.RemoveEmptyEntries);
                resul.Close();
                StreamWriter result = new StreamWriter("Result.txt");
                result.Write(string.Empty);
                result.Close();
                Line[8] = string.Empty;
                StreamWriter statistic = new StreamWriter(@"C:\Users\Admin\source\repos\Prac1\bin\Debug\Statistics.txt", true);
                if (InputField.Text == VerifField.Text)
                {
                    SymbolCount.Content = h++;
                    foreach (var item in Line)
                    {
                        statistic.Write(item + " ");
                       
                    }
                    statistic.WriteLine();
                    statistic.Close();
                    Statistic_();
                }
                statistic.Close();
                InputField.IsEnabled = true;
                Symbol.Content = 0;
                m = 1;
            }
            else
            {
                Symbol.Content = 0;
                m = 1;
                InputField.IsEnabled = false;
            }
        }
        private void Statistic_()
        {
            StreamWriter Statistic = new StreamWriter(@"C:\Users\Admin\source\repos\Prac1\bin\Debug\Statistic_.txt", true);
            double e = 0; int j = 0;
            double[] M = new double[int.Parse(Count.Text)];
            double[] S = new double[int.Parse(Count.Text)];
            double[] S_2 = new double[int.Parse(Count.Text)];
            double[] t = new double[int.Parse(Count.Text)];
            StreamReader statistic = new StreamReader("Statistics.txt");
            foreach (var line in statistic.ReadLine().Split(new char[] { '\n', '\r' }, StringSplitOptions.RemoveEmptyEntries))
            {                
                double[] y = line.Split(new char[] { ' ' }, StringSplitOptions.RemoveEmptyEntries).Where(x => !string.IsNullOrWhiteSpace(x)).Select(x => double.Parse(x)).ToArray();
                for (int i = 0; i < y.Length; i++)
                {
                    e += y[i];
                }
                M[j] = 1.0 * e / y.Length;
                e = 0;
                for (int i = 0; i < y.Length; i++)
                {
                    e += Math.Pow(y[i] - M[j], 2);
                }
                S_2[j] = 1.0 * e / (y.Length - 1);
                S[j] = Math.Sqrt(S_2[j]);
                Statistic.Write(M[j] + " " + S[j] + "\n");
                j++;
                int o = 0;
                while(o != y.Length)
                { 
                    t[o] = Math.Abs((y[o] - M[j])/(S[j]/Math.Sqrt(y.Length - 1)));                   
                    if (t[o] > 0.95) break;
                    o++;
                }
                Statistic.Close();
            }
            statistic.Close();
        }
    }
}
