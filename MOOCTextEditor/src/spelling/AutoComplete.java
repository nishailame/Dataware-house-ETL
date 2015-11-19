/**
 * 
 */
package spelling;

import java.util.List;

/**
 * @author Nisha Ilame
 * @author UC San Diego Intermediate Programming MOOC team
 *
 */
public interface AutoComplete {
	public List<String> predictCompletions(String prefix, int numCompletions);
}
