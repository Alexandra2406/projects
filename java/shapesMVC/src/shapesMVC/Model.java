package shapesMVC;

public class Model {
    Triangle t;
    Rectangle r;
    Trapezoid tz;
    Model(Triangle t_)
    {
    	t = t_;
    }
    Model(Rectangle r_)
    {
    	r = r_;
    }
    Model(Trapezoid tz_)
    {
    	tz = tz_;
    }
    Model(Triangle t_, Rectangle r_)
    {
    	t = t_;
    	r = r_;
    }
    Model(Rectangle r_, Trapezoid tz_)
    {
    	tz = tz_;
    	r = r_;
    }
    Model(Triangle t_, Rectangle r_, Trapezoid tz_)
    {
    	t = t_;
    	r = r_;
    	tz = tz_;
    }

}
