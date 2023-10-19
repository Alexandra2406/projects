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
using System.Diagnostics;
using System.IO;

namespace Prac1
{
    /// <summary>
    /// Логика взаимодействия для ProtectionModeWindow.xaml
    /// </summary>
    public partial class ProtectionModeWindow : Window
    {
        private void CloseStudyMode_Click(object sender, RoutedEventArgs e)
        {
            StreamWriter resultprotection = new StreamWriter("ResultProtection.txt");
            resultprotection.Write(string.Empty);
            MainWindow mw = new MainWindow();
            Hide();
            mw.Show();
        }
        public ProtectionModeWindow()
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
            StreamWriter resultprotection = File.AppendText("ResultProtection.txt");
            ts = stopWatch.Elapsed;
            if (count > 0 && count < 10)
            {
                t1 = ts.TotalSeconds;
                resultprotection.Write((Math.Round(t1 - t0, 2)) + " ");
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
                resultprotection.Close();
                count = 0;
                InputField.IsEnabled = false;
                Symbol.Content = m++;
                Statistic();
                InputField.Text = string.Empty;
            }
            resultprotection.Close();
        }

        private void Statistic()
        {
            if (int.Parse(CountProtection.Text) > int.Parse(Convert.ToString(SymbolCount.Content)))
            {
                StreamReader resultpro = new StreamReader("ResultProtection.txt");
                string[] Line = resultpro.ReadLine().Split(' ', '\n', (char)StringSplitOptions.RemoveEmptyEntries);
                resultpro.Close();
                StreamWriter resultprotec = new StreamWriter("ResultProtection.txt");
                resultprotec.Write(string.Empty);
                resultprotec.Close();
                Line[8] = string.Empty;
                StreamWriter statisticpro = new StreamWriter(@"C:\Users\Admin\source\repos\Prac1\bin\Debug\StatisticProtection.txt", true);
                if (InputField.Text == VerifField.Text)
                {
                    SymbolCount.Content = h++;
                    foreach (var item in Line)
                    {
                        statisticpro.Write(item + " ");
                    }
                    statisticpro.WriteLine();
                    statisticpro.Close();
                    Statistic_();
                }
                statisticpro.Close();
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
            StreamWriter Statistic_PRO = new StreamWriter(@"C:\Users\Admin\source\repos\Prac1\bin\Debug\Statistic_Protection.txt", true);
            double e = 0; int j = 0, length, g = 0;
            double[] M = new double[int.Parse(CountProtection.Text)];
            double[] S = new double[int.Parse(CountProtection.Text)];
            double[] S_2 = new double[int.Parse(CountProtection.Text)];
            double[] t = new double[int.Parse(CountProtection.Text)];
            StreamReader statistic = new StreamReader("StatisticProtection.txt");
            foreach (var line in statistic.ReadLine().Split(new char[] { '\n', '\r' }, StringSplitOptions.RemoveEmptyEntries))
            {
                double[] y = line.Split(new char[] { ' ' }, StringSplitOptions.RemoveEmptyEntries).Where(x => !string.IsNullOrWhiteSpace(x)).Select(x => double.Parse(x)).ToArray();
                length = y.Length;
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
                
                j++;
                int o = 0;
                while (o != y.Length)
                {
                    t[o] = Math.Abs((y[o] - M[j]) / (S[j] / Math.Sqrt(y.Length - 1)));
                    if (t[o] > 1.860)
                    {
                        y = null;
                        break;
                    }
                    else g = 1;
                    o++;
                }
                if (g == 1) Statistic_PRO.Write(M[j] + " " + S[j] + "\n");
                Statistic_PRO.Close();
            }
            StreamReader Statistic = new StreamReader("Statistics.txt"); 
            j = 0; int r = 0, k = 0, p = 0;
            double[] Fisher = new double[int.Parse(CountProtection.Text)];
            double[] S_ = new double[int.Parse(CountProtection.Text)];
            double[] t_ = new double[int.Parse(CountProtection.Text)];
            double[] P = new double[int.Parse(CountProtection.Text)];
            double[] P1 = new double[int.Parse(CountProtection.Text)];
            double[] P2 = new double[int.Parse(CountProtection.Text)];
            while (!Statistic.EndOfStream && j < int.Parse(CountProtection.Text))
            {
                string Line = Statistic.ReadLine();
                string[] Elem2 = Line.Split(' ');
                double Smax, Smin;
                if (Math.Pow(double.Parse(Elem2[1]), 2) >= Math.Pow(S[j], 2)) { Smax = Math.Pow(double.Parse(Elem2[1]), 2); Smin = Math.Pow(S[j], 2); }
                else { Smax = Math.Pow(S[j], 2); Smin = Math.Pow(double.Parse(Elem2[1]), 2); }
                Fisher[j] = Smax / Smin;
                if (Fisher[j] > 4.74) { p++; DispField.Content = "heterogeneous"; } //неоднорідні
                else DispField.Content = "homogeneous"; //однорідні
                S_[j] = Math.Sqrt((Math.Pow(double.Parse(Elem2[1]), 2) + Math.Pow(S[j], 2)) * (8 - 1) / (2 * 8 - 1));
                t_[j] = Math.Abs(double.Parse(Elem2[0]) + M[j]) / (S_[j] * Math.Sqrt(2.0 / 8));
                if (t_[j] > 1.860) { MessageBox.Show("не випадкова", "розбіжність"); k++; }
                else { MessageBox.Show("випадкова", "розбіжність"); r++; k++; }
                P[j] = 1.0 * r / k;
                StatisticsBlock.Content = P[j];
                P1[j] = (k - p) / k;
                P1Field.Content = P1[j];
                P2[j] = p / k;
                P2Field.Content = P2[j];
                j++; 
            }
        }
    }
}
